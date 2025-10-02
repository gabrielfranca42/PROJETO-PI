package Controller;

import DTO.MunicipioDto;
import Service.MunicipioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/municipio")
@AllArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    // Get  Municipios
    @GetMapping("/all")
    public ResponseEntity<List<MunicipioDto>> getAllMunicipios() {
        List<MunicipioDto> municipios = municipioService.getAll();
        return ResponseEntity.ok(municipios);
    }

    // Get Municipio por id
    @GetMapping("/{id}")
    public ResponseEntity<MunicipioDto> getMunicipioById(@PathVariable("id") Long municipioId) {
        MunicipioDto municipioDto = municipioService.getById(municipioId);
        if (municipioDto != null) {
            return ResponseEntity.ok(municipioDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria nova Municipio
    @PostMapping("/create")
    public ResponseEntity<MunicipioDto> createMunicipio(@ModelAttribute MunicipioDto municipioDto) {
        MunicipioDto createdMunicipio = municipioService.create(municipioDto);
        return new ResponseEntity<>(createdMunicipio, CREATED);
    }

    // Update  Municipio
    @PostMapping("/update")
    public ResponseEntity<MunicipioDto> updateMunicipio(@ModelAttribute MunicipioDto municipioDto) {
        Long municipioId = municipioDto.getIdMunicipio();
        MunicipioDto existingMunicipio = municipioService.getById(municipioId);
        if (existingMunicipio == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(municipioDto, existingMunicipio, getNullPropertyNames(municipioDto));

        MunicipioDto updatedMunicipio = municipioService.update(municipioId, existingMunicipio);
        return ResponseEntity.ok(updatedMunicipio);
    }

    // Delete Municipio by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteMunicipio(@PathVariable("id") Long municipioId) {
        MunicipioDto municipioDto = municipioService.getById(municipioId);
        if (municipioDto != null) {
            municipioService.delete(municipioId);
            return ResponseEntity.ok("Municipio deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper para obter nomes das propriedades nulas para ignorar na cópia
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
