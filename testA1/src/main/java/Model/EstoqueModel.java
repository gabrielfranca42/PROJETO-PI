package Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "estoque")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armazem_id")
    private ArmazemModel armazemModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semente_id", nullable = false)
    private SementeModel sementeModel;

    @Column(name = "quantidade_peso", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidadePeso;

    @Column(name = "quantidade_volume", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidadeVolume;

    @OneToMany(mappedBy = "estoque", fetch = FetchType.LAZY)
    private List<EstoqueControleModel> estoqueControleModelList;
}
