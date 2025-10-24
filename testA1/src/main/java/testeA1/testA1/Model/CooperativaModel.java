package testeA1.testA1.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "cooperativa")
public class CooperativaModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nome", nullable = false, length = 255)
    private String nome;


    @Column(name = "cnpj", nullable = false, length = 19, unique = true)
    private String cnpj;


    @Column(name = "telefone", nullable = false, length = 11)
    private String telefone;


    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;


    @Column(name = "cep", nullable = false, length = 8)
    private String cep;


    @Column(name = "qntd_agricultor", nullable = false)
    private Integer qntdAgricultor;


    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @ManyToOne
    @JoinColumn(name = "endereco_id") // cria a FK no banco
    private EnderecoModel enderecoModel;

    private List<EstoqueControleModel> estoqueControleModels = new ArrayList<>();

}
