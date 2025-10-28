package testeA1.testA1.Service;

import testeA1.testA1.DTO.EnderecoDto;
import testeA1.testA1.Model.EnderecoModel;
import testeA1.testA1.Repository.EnderecoRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    // Converter Model para DTO
    private EnderecoDto modelParaDto(EnderecoModel model) {
        EnderecoDto dto = new EnderecoDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private EnderecoModel dtoParaModel(EnderecoDto dto) {
        EnderecoModel model = new EnderecoModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Endereços (retorna lista de DTOs)
    public List<EnderecoDto> getAll() {
        List<EnderecoModel> models = enderecoRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Endereço por ID (retorna DTO)
    public EnderecoDto getById(Long id) {
        EnderecoModel model = enderecoRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Endereço (recebe DTO, salva Model, retorna DTO)
    public EnderecoDto create(EnderecoDto enderecoDto) {
        EnderecoModel model = dtoParaModel(enderecoDto);
        EnderecoModel salvo = enderecoRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Endereço existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public EnderecoDto update(Long id, EnderecoDto enderecoDto) {
        EnderecoModel existente = enderecoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Endereço com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(enderecoDto, existente, getNullPropertyNames(enderecoDto));

        EnderecoModel salvo = enderecoRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Endereço por ID
    public void delete(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Endereço com ID " + id + " não encontrado.");
        }
        enderecoRepository.deleteById(id);
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
