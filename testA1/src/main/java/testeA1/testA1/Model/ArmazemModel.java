package testeA1.testA1.Model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Armazen")
public class ArmazemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "cnpj", nullable = false, length = 19, unique = true)
    private String cnpj;

    @Column(name = "capacidade", nullable = false, precision = 10, scale = 2)
    private BigDecimal capacidade;

    // ALTERAÇÃO: atributo mantido como "enderecoModel", pois é referenciado por mappedBy em EnderecoModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id", nullable = false)
    private EnderecoModel enderecoModel;

    @OneToMany(mappedBy = "armazem", fetch = FetchType.LAZY)
    private List<EstoqueModel> estoqueModelList;
}

