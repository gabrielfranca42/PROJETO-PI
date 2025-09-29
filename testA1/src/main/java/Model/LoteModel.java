package Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table  (name = "Lote")
public class LoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLote;

    @Column(nullable = false, length = 10)
    private String dataEntrada;

    @Column (nullable = false, length = 10)
    private String dataSaida;

    @Column (nullable = false, length = 155)
    private String localRecebimento;

    @Column (nullable = false, length = 155)
    private String momentoAtual;


    @Column (nullable = false, length = 7, unique = true)
    private String PlacaVeiculo;


    @Column (nullable = false, length = 7, unique = true)
    private int Identificacao;

    @Column (nullable = false, length = 6)
    private int Kg;

    @Column (nullable = false, length = 6)
    private float MetrosCubicos;

    @Column (nullable = false, length = 155)
    private boolean entreguemEmBomEstado;


    @Column (nullable = false, length = 155)
    private boolean entregueEmMalEstado;

    @Column (nullable = false, length = 155)
    private boolean naoEntregue;














}
