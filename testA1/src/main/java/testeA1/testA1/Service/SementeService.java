package testeA1.testA1.Service;

import testeA1.testA1.DTO.SementeDto;
import testeA1.testA1.Model.SementeModel;
import testeA1.testA1.Repository.SementeRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SementeService {

    private final SementeRepository sementeRepository;

    // Converter Model para DTO
    private SementeDto modelParaDto(SementeModel model) {
        SementeDto dto = new SementeDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private SementeModel dtoParaModel(SementeDto dto) {
        SementeModel model = new SementeModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Sementes (retorna lista de DTOs)
    public List<SementeDto> getAll() {
        List<SementeModel> models = sementeRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Semente por ID (retorna DTO)
    public SementeDto getById(Long id) {
        SementeModel model = sementeRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar nova Semente (recebe DTO, salva Model, retorna DTO)
    public SementeDto create(SementeDto sementeDto) {
        SementeModel model = dtoParaModel(sementeDto);
        SementeModel salvo = sementeRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Semente existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public SementeDto update(Long id, SementeDto sementeDto) {
        SementeModel existente = sementeRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Semente com ID " + id + " não encontrada"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(sementeDto, existente, getNullPropertyNames(sementeDto));

        SementeModel salvo = sementeRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Semente por ID
    public void delete(Long id) {
        if (!sementeRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Semente com ID " + id + " não encontrada.");
        }
        sementeRepository.deleteById(id);
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
