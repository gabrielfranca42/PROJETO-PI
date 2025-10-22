package Controller;



import DTO.EnderecoDto;
import Service.EnderecoService;
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
@RequestMapping("/endereco")
@AllArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    // Get all Endereços
    @GetMapping("/all")
    public ResponseEntity<List<EnderecoDto>> getAllEnderecos() {
        List<EnderecoDto> enderecos = enderecoService.getAll();
        return ResponseEntity.ok(enderecos);
    }

    // Get Endereço por id
    @GetMapping("/{id}")
    public ResponseEntity<EnderecoDto> getEnderecoById(@PathVariable("id") Long enderecoId) {
        EnderecoDto enderecoDto = enderecoService.getById(enderecoId);
        if (enderecoDto != null) {
            return ResponseEntity.ok(enderecoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo Endereço
    @PostMapping("/create")
    public ResponseEntity<EnderecoDto> createEndereco(@ModelAttribute EnderecoDto enderecoDto) {
        EnderecoDto createdEndereco = enderecoService.create(enderecoDto);
        return new ResponseEntity<>(createdEndereco, CREATED);
    }

    // Update Endereço
    @PostMapping("/update")
    public ResponseEntity<EnderecoDto> updateEndereco(@ModelAttribute EnderecoDto enderecoDto) {
        Long enderecoId = enderecoDto.getId();
        EnderecoDto existingEndereco = enderecoService.getById(enderecoId);
        if (existingEndereco == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(enderecoDto, existingEndereco, getNullPropertyNames(enderecoDto));

        EnderecoDto updatedEndereco = enderecoService.update(enderecoId, existingEndereco);
        return ResponseEntity.ok(updatedEndereco);
    }

    // Delete Endereço by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteEndereco(@PathVariable("id") Long enderecoId) {
        EnderecoDto enderecoDto = enderecoService.getById(enderecoId);
        if (enderecoDto != null) {
            enderecoService.delete(enderecoId);
            return ResponseEntity.ok("Endereço deletado com sucesso!");
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
