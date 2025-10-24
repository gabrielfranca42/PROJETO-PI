package testeA1.testA1.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
//apenas criacao de entidade no banco de dados e nome da tabela


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "Semetes")

public class SementeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false, length = 100)
    private String nome;


    @Column(name = "validade")
    private Integer validade;


    @Column(name = "especie", length = 45)
    private String especie;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecimento_id", nullable = false)
    private FornecimentoModel fornecimentoModel;


    @OneToMany(mappedBy = "semente", fetch = FetchType.LAZY)
    private List<EstoqueModel> estoqueModelList;


    @OneToMany(mappedBy = "semente", fetch = FetchType.LAZY)
    private List<LoteModel> loteModelList;


    @OneToMany(mappedBy = "semente", fetch = FetchType.LAZY)
    private List<LoteControleModel> loteControleModelList;



}
