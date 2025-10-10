package Controller;

import DTO.ArmazemDto;
import Service.ArmazemService;
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
@RequestMapping("/armazem")
@AllArgsConstructor
public class ArmazemController {

    private final ArmazemService armazemService;

    // Get  Armazens
    @GetMapping("/all")
    public ResponseEntity<List<ArmazemDto>> getAllArmazens() {
        List<ArmazemDto> armazens = armazemService.getAll();
        return ResponseEntity.ok(armazens);
    }

    // Get Armazem por id
    @GetMapping("/{id}")
    public ResponseEntity<ArmazemDto> getArmazemById(@PathVariable("id") Long armazemId) {
        ArmazemDto armazemDto = armazemService.getById(armazemId);
        if (armazemDto != null) {
            return ResponseEntity.ok(armazemDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria nova Armazem
    @PostMapping("/create")
    public ResponseEntity<ArmazemDto> createArmazem(@ModelAttribute ArmazemDto armazemDto) {
        ArmazemDto createdArmazem = armazemService.create(armazemDto);
        return new ResponseEntity<>(createdArmazem, CREATED);
    }

    // Update  Armazem
    @PostMapping("/update")
    public ResponseEntity<ArmazemDto> updateArmazem(@ModelAttribute ArmazemDto armazemDto) {
        Long armazemId = armazemDto.getId();
        ArmazemDto existingArmazem = armazemService.getById(armazemId);
        if (existingArmazem == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(armazemDto, existingArmazem, getNullPropertyNames(armazemDto));

        ArmazemDto updatedArmazem = armazemService.update(armazemId, existingArmazem);
        return ResponseEntity.ok(updatedArmazem);
    }

    // Delete Armazem by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteArmazem(@PathVariable("id") Long armazemId) {
        ArmazemDto armazemDto = armazemService.getById(armazemId);
        if (armazemDto != null) {
            armazemService.delete(armazemId);
            return ResponseEntity.ok("Armazem deletado com sucesso!");
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
