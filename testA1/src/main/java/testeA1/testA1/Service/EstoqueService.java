package testeA1.testA1.Service;

import testeA1.testA1.DTO.EstoqueDto;
import testeA1.testA1.Model.EstoqueModel;
import testeA1.testA1.Repository.EstoqueRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    // Converter Model para DTO
    private EstoqueDto modelParaDto(EstoqueModel model) {
        EstoqueDto dto = new EstoqueDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private EstoqueModel dtoParaModel(EstoqueDto dto) {
        EstoqueModel model = new EstoqueModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Estoques (retorna lista de DTOs)
    public List<EstoqueDto> getAll() {
        List<EstoqueModel> models = estoqueRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Estoque por ID (retorna DTO)
    public EstoqueDto getById(Long id) {
        EstoqueModel model = estoqueRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Estoque (recebe DTO, salva Model, retorna DTO)
    public EstoqueDto create(EstoqueDto estoqueDto) {
        EstoqueModel model = dtoParaModel(estoqueDto);
        EstoqueModel salvo = estoqueRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Estoque existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public EstoqueDto update(Long id, EstoqueDto estoqueDto) {
        EstoqueModel existente = estoqueRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Estoque com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(estoqueDto, existente, getNullPropertyNames(estoqueDto));

        EstoqueModel salvo = estoqueRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Estoque por ID
    public void delete(Long id) {
        if (!estoqueRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Estoque com ID " + id + " não encontrado.");
        }
        estoqueRepository.deleteById(id);
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

