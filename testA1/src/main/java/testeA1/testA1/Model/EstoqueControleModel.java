package testeA1.testA1.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "estoque_controle")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstoqueControleModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_armazenamento", length = 155, nullable = true)
    private String localArmazenamento;



    @Column(name = "quantidade_atual", precision = 10, scale = 2, nullable = true)
    private BigDecimal quantidadeAtual;



    @Column(name = "data_ultima_atualizacao", nullable = true)
    private LocalDateTime dataUltimaAtualizacao;
    // Relações representadas (assumindo colunas FK separadas no schema corrigido)
    // FK para Estoque (relacionamento muitos-para-um, opcional)



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_estoque", referencedColumnName = "id", nullable = true) // Coluna assumida: id_estoque
    private EstoqueModel estoqueModel;
    // FK para Lote (relacionamento muitos-para-um, opcional)



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_lote", referencedColumnName = "id", nullable = true) // Coluna assumida: id_lote
    private LoteModel loteModel;
    // FK para Cooperativa (relacionamento muitos-para-um, opcional)



    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_cooperativa", referencedColumnName = "id", nullable = true) // Coluna assumida: id_cooperativa
    private CooperativaModel cooperativaModel;

}
