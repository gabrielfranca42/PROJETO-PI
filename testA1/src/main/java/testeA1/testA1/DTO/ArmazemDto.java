package testeA1.testA1.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ArmazemDto {
    private Long id;
    private String nome;
    private String cnpj;
    private BigDecimal capacidade;
    private Long enderecoId;
}
