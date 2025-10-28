package testeA1.testA1.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "lote_controle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoteControleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lote_id", nullable = false)
    private LoteModel loteModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semente_id", nullable = false)
    private SementeModel sementeModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorModel fornecedor;  // ⚠ renomeado para "fornecedor"

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime dataUltimaAtualizacao;

    @Column(name = "data_validade")
    private LocalDate dataValidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    public enum Status {
        ATIVO, ESGOTADO, VENCIDO, RETIDO
    }
}
