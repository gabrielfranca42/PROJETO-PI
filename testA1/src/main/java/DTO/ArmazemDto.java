package DTO;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArmazemDto {
    private Long idArmazen;
    private String nome;
    private float metroCubicos;
    private String cnpj;
    private LocalDateTime registroDeEntrada;
    private LocalDateTime registroDeSaida;
}
