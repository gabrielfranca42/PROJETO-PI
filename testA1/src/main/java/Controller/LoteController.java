package Controller;

import DTO.LoteDto;
import Service.LoteService;
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
@RequestMapping("/lote")
@AllArgsConstructor
public class LoteController {

    private final LoteService loteService;

    // Get  Lotes
    @GetMapping("/all")
    public ResponseEntity<List<LoteDto>> getAllLotes() {
        List<LoteDto> lotes = loteService.getAll();
        return ResponseEntity.ok(lotes);
    }

    // Get Lote por id
    @GetMapping("/{id}")
    public ResponseEntity<LoteDto> getLoteById(@PathVariable("id") Long loteId) {
        LoteDto loteDto = loteService.getById(loteId);
        if (loteDto != null) {
            return ResponseEntity.ok(loteDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria nova Lote
    @PostMapping("/create")
    public ResponseEntity<LoteDto> createLote(@ModelAttribute LoteDto loteDto) {
        LoteDto createdLote = loteService.create(loteDto);
        return new ResponseEntity<>(createdLote, CREATED);
    }

    // Update  Lote
    @PostMapping("/update")
    public ResponseEntity<LoteDto> updateLote(@ModelAttribute LoteDto loteDto) {
        Long loteId = loteDto.getIdLote();
        LoteDto existingLote = loteService.getById(loteId);
        if (existingLote == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(loteDto, existingLote, getNullPropertyNames(loteDto));

        LoteDto updatedLote = loteService.update(loteId, existingLote);
        return ResponseEntity.ok(updatedLote);
    }

    // Delete Lote by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteLote(@PathVariable("id") Long loteId) {
        LoteDto loteDto = loteService.getById(loteId);
        if (loteDto != null) {
            loteService.delete(loteId);
            return ResponseEntity.ok("Lote deletado com sucesso!");
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
