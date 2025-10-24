package testeA1.testA1.Model;



import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "estoque")
public class EstoqueModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal quantidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "armazem_id", nullable = false)
    private ArmazemModel armazem;
    // ALTERAÇÃO: agora o estoque aponta para o ArmazemModel, não para EnderecoModel

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semente_id", nullable = false)
    private SementeModel semente;
    // ALTERAÇÃO: relaciona corretamente a semente armazenada
}

