package testeA1.testA1.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoteDto {

    private Long id;
    private Long sementeId;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private BigDecimal peso;
    private BigDecimal metroCubico;
    private String identificacao;
    private List<Long> loteControleIds;
    private List<Long> estoqueControleIds;
}
