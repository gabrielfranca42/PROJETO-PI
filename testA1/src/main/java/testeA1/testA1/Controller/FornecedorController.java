package testeA1.testA1.Controller;

import testeA1.testA1.DTO.FornecedorDto;
import testeA1.testA1.Service.FornecedorService;
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
@RequestMapping("/fornecedor")
@AllArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    // Get all Fornecedores
    @GetMapping("/all")
    public ResponseEntity<List<FornecedorDto>> getAllFornecedores() {
        List<FornecedorDto> fornecedores = fornecedorService.getAll();
        return ResponseEntity.ok(fornecedores);
    }

    // Get Fornecedor por id
    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDto> getFornecedorById(@PathVariable("id") Long fornecedorId) {
        FornecedorDto fornecedorDto = fornecedorService.getById(fornecedorId);
        if (fornecedorDto != null) {
            return ResponseEntity.ok(fornecedorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo Fornecedor
    @PostMapping("/create")
    public ResponseEntity<FornecedorDto> createFornecedor(@ModelAttribute FornecedorDto fornecedorDto) {
        FornecedorDto createdFornecedor = fornecedorService.create(fornecedorDto);
        return new ResponseEntity<>(createdFornecedor, CREATED);
    }

    // Update Fornecedor
    @PostMapping("/update")
    public ResponseEntity<FornecedorDto> updateFornecedor(@ModelAttribute FornecedorDto fornecedorDto) {
        Long fornecedorId = fornecedorDto.getId();
        FornecedorDto existingFornecedor = fornecedorService.getById(fornecedorId);
        if (existingFornecedor == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(fornecedorDto, existingFornecedor, getNullPropertyNames(fornecedorDto));

        FornecedorDto updatedFornecedor = fornecedorService.update(fornecedorId, existingFornecedor);
        return ResponseEntity.ok(updatedFornecedor);
    }

    // Delete Fornecedor by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteFornecedor(@PathVariable("id") Long fornecedorId) {
        FornecedorDto fornecedorDto = fornecedorService.getById(fornecedorId);
        if (fornecedorDto != null) {
            fornecedorService.delete(fornecedorId);
            return ResponseEntity.ok("Fornecedor deletado com sucesso!");
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
