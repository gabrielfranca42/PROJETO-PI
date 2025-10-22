package Controller;
import DTO.EstoqueDto;
import Service.EstoqueService;
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
@RequestMapping("/estoque")
@AllArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    // Get all Estoques
    @GetMapping("/all")
    public ResponseEntity<List<EstoqueDto>> getAllEstoques() {
        List<EstoqueDto> estoques = estoqueService.getAll();
        return ResponseEntity.ok(estoques);
    }

    // Get Estoque por id
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueDto> getEstoqueById(@PathVariable("id") Long estoqueId) {
        EstoqueDto estoqueDto = estoqueService.getById(estoqueId);
        if (estoqueDto != null) {
            return ResponseEntity.ok(estoqueDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo Estoque
    @PostMapping("/create")
    public ResponseEntity<EstoqueDto> createEstoque(@ModelAttribute EstoqueDto estoqueDto) {
        EstoqueDto createdEstoque = estoqueService.create(estoqueDto);
        return new ResponseEntity<>(createdEstoque, CREATED);
    }

    // Update Estoque
    @PostMapping("/update")
    public ResponseEntity<EstoqueDto> updateEstoque(@ModelAttribute EstoqueDto estoqueDto) {
        Long estoqueId = estoqueDto.getId();
        EstoqueDto existingEstoque = estoqueService.getById(estoqueId);
        if (existingEstoque == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(estoqueDto, existingEstoque, getNullPropertyNames(estoqueDto));

        EstoqueDto updatedEstoque = estoqueService.update(estoqueId, existingEstoque);
        return ResponseEntity.ok(updatedEstoque);
    }

    // Delete Estoque by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteEstoque(@PathVariable("id") Long estoqueId) {
        EstoqueDto estoqueDto = estoqueService.getById(estoqueId);
        if (estoqueDto != null) {
            estoqueService.delete(estoqueId);
            return ResponseEntity.ok("Estoque deletado com sucesso!");
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
