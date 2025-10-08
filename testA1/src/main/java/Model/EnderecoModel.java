package Model;

import jakarta.persistence.*;

import java.util.List;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_id")
    private MunicipioModel municipioModel;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    private FornecedorModel fornecedorModel;


    @OneToMany(mappedBy = "endereco", fetch = FetchType.LAZY)
    private List<CooperativaModel> cooperativasModelList;


    @OneToMany(mappedBy = "endereco", fetch = FetchType.LAZY)
    private List<ArmazemModel> armazemModelList;

}
