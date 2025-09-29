package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoteDto {
    private Long idLote;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private String localRecebimento;
    private String momentoAtual;
    private String placaVeiculo;
    private int identificacao;
    private int kg;
    private float metrosCubicos;
    private boolean entreguemEmBomEstado;
    private boolean entregueEmMalEstado;
    private boolean naoEntregue;
}
