package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ArmazemDto {
    private Long idArmazen;
    private String nome;
    private float metroCubicos;
    private String cnpj;
    private LocalDateTime registroDeEntrada;
    private LocalDateTime registroDeSaida;

}
