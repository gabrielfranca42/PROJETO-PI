package testeA1.testA1.Service;

import testeA1.testA1.DTO.FornecedorDto;
import testeA1.testA1.Model.FornecedorModel;
import testeA1.testA1.Repository.FornecedorRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    // Converter Model para DTO
    private FornecedorDto modelParaDto(FornecedorModel model) {
        FornecedorDto dto = new FornecedorDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private FornecedorModel dtoParaModel(FornecedorDto dto) {
        FornecedorModel model = new FornecedorModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Fornecedores (retorna lista de DTOs)
    public List<FornecedorDto> getAll() {
        List<FornecedorModel> models = fornecedorRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Fornecedor por ID (retorna DTO)
    public FornecedorDto getById(Long id) {
        FornecedorModel model = fornecedorRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Fornecedor (recebe DTO, salva Model, retorna DTO)
    public FornecedorDto create(FornecedorDto fornecedorDto) {
        FornecedorModel model = dtoParaModel(fornecedorDto);
        FornecedorModel salvo = fornecedorRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Fornecedor existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public FornecedorDto update(Long id, FornecedorDto fornecedorDto) {
        FornecedorModel existente = fornecedorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Fornecedor com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(fornecedorDto, existente, getNullPropertyNames(fornecedorDto));

        FornecedorModel salvo = fornecedorRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Fornecedor por ID
    public void delete(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Fornecedor com ID " + id + " não encontrado.");
        }
        fornecedorRepository.deleteById(id);
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
