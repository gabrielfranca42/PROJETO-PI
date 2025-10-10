package DTO;

import ch.qos.logback.core.status.Status;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class LoteControleDto {


    private Long id;
    private Long loteId;
    private Long sementeId;
    private Long fornecedorId;
    private LocalDateTime dataUltimaAtualizacao;
    private LocalDate dataValidade;
    private Status status;
}
