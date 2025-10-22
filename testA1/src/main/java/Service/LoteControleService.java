package Service;

import DTO.LoteControleDto;
import Model.LoteControleModel;
import Repository.LoteControleRepository;
import Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoteControleService {

    private final LoteControleRepository loteControleRepository;

    // Converter Model para DTO
    private LoteControleDto modelParaDto(LoteControleModel model) {
        LoteControleDto dto = new LoteControleDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private LoteControleModel dtoParaModel(LoteControleDto dto) {
        LoteControleModel model = new LoteControleModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os LoteControles (retorna lista de DTOs)
    public List<LoteControleDto> getAll() {
        List<LoteControleModel> models = loteControleRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar LoteControle por ID (retorna DTO)
    public LoteControleDto getById(Long id) {
        LoteControleModel model = loteControleRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo LoteControle (recebe DTO, salva Model, retorna DTO)
    public LoteControleDto create(LoteControleDto loteControleDto) {
        LoteControleModel model = dtoParaModel(loteControleDto);
        LoteControleModel salvo = loteControleRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar LoteControle existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public LoteControleDto update(Long id, LoteControleDto loteControleDto) {
        LoteControleModel existente = loteControleRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("LoteControle com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(loteControleDto, existente, getNullPropertyNames(loteControleDto));

        LoteControleModel salvo = loteControleRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar LoteControle por ID
    public void delete(Long id) {
        if (!loteControleRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("LoteControle com ID " + id + " não encontrado.");
        }
        loteControleRepository.deleteById(id);
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
