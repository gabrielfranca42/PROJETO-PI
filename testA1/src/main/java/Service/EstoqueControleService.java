package Service;

import DTO.EstoqueControleDto;
import Model.EstoqueControleModel;
import Repository.EstoqueControleRepository;
import Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EstoqueControleService {

    private final EstoqueControleRepository estoqueControleRepository;

    // Converter Model para DTO
    private EstoqueControleDto modelParaDto(EstoqueControleModel model) {
        EstoqueControleDto dto = new EstoqueControleDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private EstoqueControleModel dtoParaModel(EstoqueControleDto dto) {
        EstoqueControleModel model = new EstoqueControleModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os EstoqueControles (retorna lista de DTOs)
    public List<EstoqueControleDto> getAll() {
        List<EstoqueControleModel> models = estoqueControleRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar EstoqueControle por ID (retorna DTO)
    public EstoqueControleDto getById(Long id) {
        EstoqueControleModel model = estoqueControleRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo EstoqueControle (recebe DTO, salva Model, retorna DTO)
    public EstoqueControleDto create(EstoqueControleDto estoqueControleDto) {
        EstoqueControleModel model = dtoParaModel(estoqueControleDto);
        EstoqueControleModel salvo = estoqueControleRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar EstoqueControle existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public EstoqueControleDto update(Long id, EstoqueControleDto estoqueControleDto) {
        EstoqueControleModel existente = estoqueControleRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("EstoqueControle com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(estoqueControleDto, existente, getNullPropertyNames(estoqueControleDto));

        EstoqueControleModel salvo = estoqueControleRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar EstoqueControle por ID
    public void delete(Long id) {
        if (!estoqueControleRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("EstoqueControle com ID " + id + " não encontrado.");
        }
        estoqueControleRepository.deleteById(id);
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
