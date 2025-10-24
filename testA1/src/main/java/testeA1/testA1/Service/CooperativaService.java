package testeA1.testA1.Service;



import testeA1.testA1.DTO.CooperativaDto;
import testeA1.testA1.Model.CooperativaModel;
import testeA1.testA1.Repository.CooperativaRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CooperativaService {

    private final CooperativaRepository cooperativaRepository;

    // Converter Model para DTO
    private CooperativaDto modelParaDto(CooperativaModel model) {
        CooperativaDto dto = new CooperativaDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private CooperativaModel dtoParaModel(CooperativaDto dto) {
        CooperativaModel model = new CooperativaModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Agricultores (retorna lista de DTOs)
    public List<CooperativaDto> getAll() {
        List<CooperativaModel> models = cooperativaRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Agricultor por ID (retorna DTO)
    public CooperativaDto getById(Long id) {
        CooperativaModel model = cooperativaRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Agricultor (recebe DTO, salva Model, retorna DTO)
    public CooperativaDto create(CooperativaDto cooperativaDto) {
        CooperativaModel model = dtoParaModel(cooperativaDto);
        CooperativaModel salvo = cooperativaRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Agricultor existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public CooperativaDto update(Long id, CooperativaDto cooperativaDto) {
        CooperativaModel existente = cooperativaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agricultor com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(cooperativaDto, existente, getNullPropertyNames(cooperativaDto));

        CooperativaModel salvo = cooperativaRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Agricultor por ID
    public void delete(Long id) {
        if (!cooperativaRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Agricultor com ID " + id + " não encontrado.");
        }
        cooperativaRepository.deleteById(id);
    }

    // Helper para obter nomes das propriedades nulas para ignorar na cópia parcial
    private static String[] getNullPropertyNames(Object source) {
        org.springframework.beans.BeanWrapper src = new org.springframework.beans.BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        java.util.Set<String> emptyNames = new java.util.HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
