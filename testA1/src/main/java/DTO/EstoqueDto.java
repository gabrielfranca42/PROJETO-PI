package DTO;

import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstoqueDto {

    private Long id;
    private Long armazemId;
    private Long sementeId;
    private BigDecimal quantidadePeso;
    private BigDecimal quantidadeVolume;
}
