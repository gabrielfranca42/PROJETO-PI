package testeA1.testA1.Service;



import testeA1.testA1.DTO.ArmazemDto;
import testeA1.testA1.Model.ArmazemModel;
import testeA1.testA1.Repository.ArmazemRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArmazemService {

    private final ArmazemRepository armazemRepository;

    // Converter Model para DTO
    private ArmazemDto modelParaDto(ArmazemModel model) {
        ArmazemDto dto = new ArmazemDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private ArmazemModel dtoParaModel(ArmazemDto dto) {
        ArmazemModel model = new ArmazemModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Armazens (retorna lista de DTOs)
    public List<ArmazemDto> getAll() {
        List<ArmazemModel> models = armazemRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Armazem por ID (retorna DTO)
    public ArmazemDto getById(Long id) {
        ArmazemModel model = armazemRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar nova Armazem (recebe DTO, salva Model, retorna DTO)
    public ArmazemDto create(ArmazemDto armazemDto) {
        ArmazemModel model = dtoParaModel(armazemDto);
        ArmazemModel salvo = armazemRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Armazem existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public ArmazemDto update(Long id, ArmazemDto armazemDto) {
        ArmazemModel existente = armazemRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Armazem com ID " + id + " não encontrada"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(armazemDto, existente, getNullPropertyNames(armazemDto));

        ArmazemModel salvo = armazemRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Armazem por ID
    public void delete(Long id) {
        if (!armazemRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Armazem com ID " + id + " não encontrada.");
        }
        armazemRepository.deleteById(id);
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
