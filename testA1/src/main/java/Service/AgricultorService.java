package Service;



import DTO.AgricultorDto;
import Model.AgricultorModel;
import Repository.AgricultorRepository;
import Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgricultorService {

    private final AgricultorRepository agricultorRepository;

    // Converter Model para DTO
    private AgricultorDto modelParaDto(AgricultorModel model) {
        AgricultorDto dto = new AgricultorDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private AgricultorModel dtoParaModel(AgricultorDto dto) {
        AgricultorModel model = new AgricultorModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Agricultores (retorna lista de DTOs)
    public List<AgricultorDto> getAll() {
        List<AgricultorModel> models = agricultorRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Agricultor por ID (retorna DTO)
    public AgricultorDto getById(Long id) {
        AgricultorModel model = agricultorRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Agricultor (recebe DTO, salva Model, retorna DTO)
    public AgricultorDto create(AgricultorDto agricultorDto) {
        AgricultorModel model = dtoParaModel(agricultorDto);
        AgricultorModel salvo = agricultorRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Agricultor existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public AgricultorDto update(Long id, AgricultorDto agricultorDto) {
        AgricultorModel existente = agricultorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Agricultor com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(agricultorDto, existente, getNullPropertyNames(agricultorDto));

        AgricultorModel salvo = agricultorRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Agricultor por ID
    public void delete(Long id) {
        if (!agricultorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Agricultor com ID " + id + " não encontrado.");
        }
        agricultorRepository.deleteById(id);
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
