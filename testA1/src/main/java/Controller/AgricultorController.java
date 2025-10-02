package Controller;

import DTO.AgricultorDto;
import Service.AgricultorService;
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
public class AgricultorController {

    private final AgricultorService agricultorService;

    // Get all Agricultores
    @GetMapping("/all")
    public ResponseEntity<List<AgricultorDto>> getAllAgricultores() {
        List<AgricultorDto> agricultores = agricultorService.getAll();
        return ResponseEntity.ok(agricultores);
    }

    // Get Agricultor por id
    @GetMapping("/{id}")
    public ResponseEntity<AgricultorDto> getAgricultorById(@PathVariable("id") Long agricultorId) {
        AgricultorDto agricultorDto = agricultorService.getById(agricultorId);
        if (agricultorDto != null) {
            return ResponseEntity.ok(agricultorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cria novo Agricultor
    @PostMapping("/create")
    public ResponseEntity<AgricultorDto> createAgricultor(@ModelAttribute AgricultorDto agricultorDto) {
        AgricultorDto createdAgricultor = agricultorService.create(agricultorDto);
        return new ResponseEntity<>(createdAgricultor, CREATED);
    }

    // Update Agricultor
    @PostMapping("/update")
    public ResponseEntity<AgricultorDto> updateAgricultor(@ModelAttribute AgricultorDto agricultorDto) {
        Long agricultorId = agricultorDto.getIdAgricultor();
        AgricultorDto existingAgricultor = agricultorService.getById(agricultorId);
        if (existingAgricultor == null) {
            return ResponseEntity.notFound().build();
        }
        // Partial update: copia propriedades não nulas do DTO para o existente
        BeanUtils.copyProperties(agricultorDto, existingAgricultor, getNullPropertyNames(agricultorDto));

        AgricultorDto updatedAgricultor = agricultorService.update(agricultorId, existingAgricultor);
        return ResponseEntity.ok(updatedAgricultor);
    }

    // Delete Agricultor by id
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteAgricultor(@PathVariable("id") Long agricultorId) {
        AgricultorDto agricultorDto = agricultorService.getById(agricultorId);
        if (agricultorDto != null) {
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
