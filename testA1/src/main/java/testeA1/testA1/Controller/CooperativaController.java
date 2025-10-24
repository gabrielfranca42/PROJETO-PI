package testeA1.testA1.Controller;

import testeA1.testA1.DTO.CooperativaDto;
import testeA1.testA1.Service.CooperativaService;
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
@RequestMapping("/agricultor")
@AllArgsConstructor
public class CooperativaController {

    private final CooperativaService agricultorService;

    // Get all Agricultores
    @GetMapping("/all")
    public ResponseEntity<List<CooperativaDto>> getAllAgricultores() {
        List<CooperativaDto> agricultores = agricultorService.getAll();
        return ResponseEntity.ok(agricultores);
    }

    // Get Agricultor por id
    @GetMapping("/{id}")
    public ResponseEntity<CooperativaDto> getAgricultorById(@PathVariable("id") Long agricultorId) {
        CooperativaDto cooperativaDto = agricultorService.getById(agricultorId);
        if (cooperativaDto != null) {
            return ResponseEntity.ok(cooperativaDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo Agricultor
    @PostMapping("/create")
    public ResponseEntity<CooperativaDto> createAgricultor(@ModelAttribute CooperativaDto cooperativaDto) {
        CooperativaDto createdAgricultor = agricultorService.create(cooperativaDto);
        return new ResponseEntity<>(createdAgricultor, CREATED);
    }

    // Update Agricultor
    @PostMapping("/update")
    public ResponseEntity<CooperativaDto> updateAgricultor(@ModelAttribute CooperativaDto cooperativaDto) {
        Long agricultorId = cooperativaDto.getIdCooperativa();
        CooperativaDto existingAgricultor = agricultorService.getById(agricultorId);
        if (existingAgricultor == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(cooperativaDto, existingAgricultor, getNullPropertyNames(cooperativaDto));

        CooperativaDto updatedAgricultor = agricultorService.update(agricultorId, existingAgricultor);
        return ResponseEntity.ok(updatedAgricultor);
    }

    // Delete Agricultor by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteAgricultor(@PathVariable("id") Long agricultorId) {
        CooperativaDto cooperativaDto = agricultorService.getById(agricultorId);
        if (cooperativaDto != null) {
            agricultorService.delete(agricultorId);
            return ResponseEntity.ok("Agricultor deletado com sucesso!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //o trecho acima vem do meu codigo nubank adptado deve de maneira obrigatorio ser mostrado ao professor e ele aprovar caso diga que ta boom usar este modelo para todos os controller

    //o trecho abaixo veio por uma idea de um projeto open sorce

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
