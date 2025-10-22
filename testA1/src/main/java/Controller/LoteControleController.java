package Controller;



import DTO.LoteControleDto;
import Service.LoteControleService;
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
@RequestMapping("/lote-controle")
@AllArgsConstructor
public class LoteControleController {

    private final LoteControleService loteControleService;

    // Get all LoteControles
    @GetMapping("/all")
    public ResponseEntity<List<LoteControleDto>> getAllLoteControles() {
        List<LoteControleDto> loteControles = loteControleService.getAll();
        return ResponseEntity.ok(loteControles);
    }

    // Get LoteControle por id
    @GetMapping("/{id}")
    public ResponseEntity<LoteControleDto> getLoteControleById(@PathVariable("id") Long loteControleId) {
        LoteControleDto loteControleDto = loteControleService.getById(loteControleId);
        if (loteControleDto != null) {
            return ResponseEntity.ok(loteControleDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo LoteControle
    @PostMapping("/create")
    public ResponseEntity<LoteControleDto> createLoteControle(@ModelAttribute LoteControleDto loteControleDto) {
        LoteControleDto createdLoteControle = loteControleService.create(loteControleDto);
        return new ResponseEntity<>(createdLoteControle, CREATED);
    }

    // Update LoteControle
    @PostMapping("/update")
    public ResponseEntity<LoteControleDto> updateLoteControle(@ModelAttribute LoteControleDto loteControleDto) {
        Long loteControleId = loteControleDto.getId();
        LoteControleDto existingLoteControle = loteControleService.getById(loteControleId);
        if (existingLoteControle == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(loteControleDto, existingLoteControle, getNullPropertyNames(loteControleDto));

        LoteControleDto updatedLoteControle = loteControleService.update(loteControleId, existingLoteControle);
        return ResponseEntity.ok(updatedLoteControle);
    }

    // Delete LoteControle by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoteControle(@PathVariable("id") Long loteControleId) {
        LoteControleDto loteControleDto = loteControleService.getById(loteControleId);
        if (loteControleDto != null) {
            loteControleService.delete(loteControleId);
            return ResponseEntity.ok("LoteControle deletado com sucesso!");
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

