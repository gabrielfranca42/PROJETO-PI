package testeA1.testA1.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "endereco")
public class EnderecoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rua", length = 155)
    private String rua;

    @Column(name = "numero", length = 10)
    private String numero;

    @Column(name = "complemento", length = 155)
    private String complemento;

    @Column(name = "bairro", length = 155)
    private String bairro;

    @Column(name = "cidade", length = 155)
    private String cidade;

    @Column(name = "estado", length = 2)
    private String estado;

    @Column(name = "cep", length = 10)
    private String cep;

    @Column(name = "ponto_referencia", length = 155)
    private String pontoReferencia;

    // Alteração: 'municipioModel' deve referenciar o atributo correto 'municipio' da classe MunicipioModel
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_id")
    private MunicipioModel municipioModel;

    // Alteração: 'fornecedorModel' não existe mapeamento de Endereco para Fornecedor no código fornecido,
    // então mantive o atributo mas sem mappedBy, pois a relação é unidirecional do Fornecedor para Endereco.
    // Caso queira bidirecional, é preciso criar 'enderecoModel' em FornecedorModel.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private FornecedorModel fornecedorModel;

    // Alteração: mapeamento correto para CooperativaModel
    // O 'mappedBy' deve ser exatamente o nome do atributo na classe CooperativaModel que aponta para EnderecoModel.
    // Como não foi fornecido o código da CooperativaModel, coloquei um placeholder 'enderecoModel' que você precisa ajustar
    @OneToMany(mappedBy = "enderecoModel", fetch = FetchType.LAZY)
    private List<CooperativaModel> cooperativasModelList;

    // Alteração: mapeamento correto para ArmazemModel
    // O atributo que referencia EnderecoModel na classe ArmazemModel é 'enderecoModel'
    @OneToMany(mappedBy = "enderecoModel", fetch = FetchType.LAZY)
    private List<ArmazemModel> armazemModelList;
}

