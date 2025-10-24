package testeA1.testA1.Service;



import testeA1.testA1.DTO.LoteDto;
import testeA1.testA1.Model.LoteModel;
import testeA1.testA1.Repository.LoteRepository;
import testeA1.testA1.Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LoteService {

    private final LoteRepository loteRepository;

    // Converter Model para DTO
    private LoteDto modelParaDto(LoteModel model) {
        LoteDto dto = new LoteDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private LoteModel dtoParaModel(LoteDto dto) {
        LoteModel model = new LoteModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Lotes (retorna lista de DTOs)
    public List<LoteDto> getAll() {
        List<LoteModel> models = loteRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Lote por ID (retorna DTO)
    public LoteDto getById(Long id) {
        LoteModel model = loteRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar novo Lote (recebe DTO, salva Model, retorna DTO)
    public LoteDto create(LoteDto loteDto) {
        LoteModel model = dtoParaModel(loteDto);
        LoteModel salvo = loteRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Lote existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public LoteDto update(Long id, LoteDto loteDto) {
        LoteModel existente = loteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Lote com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(loteDto, existente, getNullPropertyNames(loteDto));

        LoteModel salvo = loteRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Lote por ID
    public void delete(Long id) {
        if (!loteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Lote com ID " + id + " não encontrado.");
        }
        loteRepository.deleteById(id);
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
