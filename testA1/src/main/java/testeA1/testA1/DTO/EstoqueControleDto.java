package testeA1.testA1.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EstoqueControleDto {

    private Long id;
    private String localArmazenamento;
    private BigDecimal quantidadeAtual;
    private LocalDateTime dataUltimaAtualizacao;
    private Long estoqueId;
    private Long loteId;
    private Long cooperativaId;
}
