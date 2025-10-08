package Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table  (name = "Lote")
public class LoteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semente_id", nullable = false)
    private SementeModel sementeModel;

    @Column(name = "data_entrada")
    private LocalDateTime dataEntrada;

    @Column(name = "data_saida")
    private LocalDateTime dataSaida;

    @Column(name = "peso", nullable = false, precision = 8, scale = 2)
    private BigDecimal peso;

    @Column(name = "metro_cubico", nullable = false, precision = 8, scale = 2)
    private BigDecimal metroCubico;

    @Column(name = "identificacao", nullable = false, length = 45, unique = true)
    private String identificacao;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<LoteControleModel> loteControleModelList;

    @OneToMany(mappedBy = "lote", fetch = FetchType.LAZY)
    private List<EstoqueControleModel> estoqueControleModelList;













}
