
import DTO.SementeDto;
import Service.SementeService;
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
@RequestMapping("/semente")
@AllArgsConstructor
public class SementeController {

    private final SementeService sementeService;

    // Get all Sementes
    @GetMapping("/all")
    public ResponseEntity<List<SementeDto>> getAllSementes() {
        List<SementeDto> sementes = sementeService.getAll();
        return ResponseEntity.ok(sementes);
    }

    // Get Semente by id
    @GetMapping("/{id}")
    public ResponseEntity<SementeDto> getSementeById(@PathVariable("id") Long sementeId) {
        SementeDto sementeDto = sementeService.getById(sementeId);
        if (sementeDto != null) {
            return ResponseEntity.ok(sementeDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create new Semente
    @PostMapping("/create")
    public ResponseEntity<SementeDto> createSemente(@ModelAttribute SementeDto sementeDto) {
        SementeDto createdSemente = sementeService.create(sementeDto);
        return new ResponseEntity<>(createdSemente, CREATED);
    }

    // Update existing Semente
    @PostMapping("/update")
    public ResponseEntity<SementeDto> updateSemente(@ModelAttribute SementeDto sementeDto) {
        Long sementeId = sementeDto.getId();
        SementeDto existingSemente = sementeService.getById(sementeId);
        if (existingSemente == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(sementeDto, existingSemente, getNullPropertyNames(sementeDto));

        SementeDto updatedSemente = sementeService.update(sementeId, existingSemente);
        return ResponseEntity.ok(updatedSemente);
    }

    // Delete Semente by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteSemente(@PathVariable("id") Long sementeId) {
        SementeDto sementeDto = sementeService.getById(sementeId);
        if (sementeDto != null) {
            sementeService.delete(sementeId);
            return ResponseEntity.ok("Semente deletada com sucesso!");
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
