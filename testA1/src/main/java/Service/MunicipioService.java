package Service;


import DTO.MunicipioDto;
import Model.MunicipioModel;
import Repository.MunicipioRepository;
import Exceptions.RecursoNaoEncontradoException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MunicipioService {

    private final MunicipioRepository municipioRepository;

    // Converter Model para DTO
    private MunicipioDto modelParaDto(MunicipioModel model) {
        MunicipioDto dto = new MunicipioDto();
        BeanUtils.copyProperties(model, dto);
        return dto;
    }

    // Converter DTO para Model
    private MunicipioModel dtoParaModel(MunicipioDto dto) {
        MunicipioModel model = new MunicipioModel();
        BeanUtils.copyProperties(dto, model);
        return model;
    }

    // Listar todos os Municipios (retorna lista de DTOs)
    public List<MunicipioDto> getAll() {
        List<MunicipioModel> models = municipioRepository.findAll();
        return models.stream()
                .map(this::modelParaDto)
                .collect(Collectors.toList());
    }

    // Buscar Municipio por ID (retorna DTO)
    public MunicipioDto getById(Long id) {
        MunicipioModel model = municipioRepository.findById(id)
                .orElse(null);
        if (model == null) {
            return null;
        }
        return modelParaDto(model);
    }

    // Criar nova Municipio (recebe DTO, salva Model, retorna DTO)
    public MunicipioDto create(MunicipioDto municipioDto) {
        MunicipioModel model = dtoParaModel(municipioDto);
        MunicipioModel salvo = municipioRepository.save(model);
        return modelParaDto(salvo);
    }

    // Atualizar Municipio existente (recebe id e DTO atualizado, retorna DTO atualizado)
    public MunicipioDto update(Long id, MunicipioDto municipioDto) {
        MunicipioModel existente = municipioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Municipio com ID " + id + " não encontrado"));

        // Copiar propriedades não nulas do DTO para a entidade existente
        BeanUtils.copyProperties(municipioDto, existente, getNullPropertyNames(municipioDto));

        MunicipioModel salvo = municipioRepository.save(existente);
        return modelParaDto(salvo);
    }

    // Deletar Municipio por ID
    public void delete(Long id) {
        if (!municipioRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Municipio com ID " + id + " não encontrado.");
        }
        municipioRepository.deleteById(id);
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
