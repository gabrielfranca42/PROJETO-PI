package Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "fornecedor")
public class FornecedorModel {
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

//DEVE SER REVISAOD ESTA PARTE DE FORNECIMENTO E POSSIVELMENTE APAGADO

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<FornecimentoModel> fornecimentoModels;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<LoteControleModel> loteControleModels;

}
