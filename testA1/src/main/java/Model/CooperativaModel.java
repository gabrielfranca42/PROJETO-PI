package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
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


    @ManyToOne(fetch = FetchType.LAZY)
    private List<EstoqueControleModel> estoqueControleModels;



}
