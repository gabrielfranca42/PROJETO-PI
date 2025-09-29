package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LoteDto {
    private Long idLote;
    private String dataEntrada;
    private String dataSaida;
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
