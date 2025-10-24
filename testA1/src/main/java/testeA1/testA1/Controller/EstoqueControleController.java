package testeA1.testA1.Controller;


import testeA1.testA1.DTO.EstoqueControleDto;
import testeA1.testA1.Service.EstoqueControleService;
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
@RequestMapping("/estoque-controle")
@AllArgsConstructor
public class EstoqueControleController {

    private final EstoqueControleService estoqueControleService;

    // Get all EstoqueControles
    @GetMapping("/all")
    public ResponseEntity<List<EstoqueControleDto>> getAllEstoqueControles() {
        List<EstoqueControleDto> estoqueControles = estoqueControleService.getAll();
        return ResponseEntity.ok(estoqueControles);
    }

    // Get EstoqueControle por id
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueControleDto> getEstoqueControleById(@PathVariable("id") Long estoqueControleId) {
        EstoqueControleDto estoqueControleDto = estoqueControleService.getById(estoqueControleId);
        if (estoqueControleDto != null) {
            return ResponseEntity.ok(estoqueControleDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo EstoqueControle
    @PostMapping("/create")
    public ResponseEntity<EstoqueControleDto> createEstoqueControle(@ModelAttribute EstoqueControleDto estoqueControleDto) {
        EstoqueControleDto createdEstoqueControle = estoqueControleService.create(estoqueControleDto);
        return new ResponseEntity<>(createdEstoqueControle, CREATED);
    }

    // Update EstoqueControle
    @PostMapping("/update")
    public ResponseEntity<EstoqueControleDto> updateEstoqueControle(@ModelAttribute EstoqueControleDto estoqueControleDto) {
        Long estoqueControleId = estoqueControleDto.getId();
        EstoqueControleDto existingEstoqueControle = estoqueControleService.getById(estoqueControleId);
        if (existingEstoqueControle == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(estoqueControleDto, existingEstoqueControle, getNullPropertyNames(estoqueControleDto));

        EstoqueControleDto updatedEstoqueControle = estoqueControleService.update(estoqueControleId, existingEstoqueControle);
        return ResponseEntity.ok(updatedEstoqueControle);
    }

    // Delete EstoqueControle by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteEstoqueControle(@PathVariable("id") Long estoqueControleId) {
        EstoqueControleDto estoqueControleDto = estoqueControleService.getById(estoqueControleId);
        if (estoqueControleDto != null) {
            estoqueControleService.delete(estoqueControleId);
            return ResponseEntity.ok("EstoqueControle deletado com sucesso!");
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
