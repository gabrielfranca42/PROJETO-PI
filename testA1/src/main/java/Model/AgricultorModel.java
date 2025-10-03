package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "agricultor")
public class AgricultorModel {

    //apenas criacao de entidade no banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgricultor;

    //criacao de id e sua estrategia

    @Column(nullable = false, length = 150)
    private String nome;

    //criacao de coluna dentro da tabela

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;
    private LocalDate dataNascimento;

    //criacao de coluna dentro da tabela

    @Column(length = 15)
    private String telefone;

    //criacao de coluna dentro da tabela

    @Column(length = 120)
    private String email;

    //criacao de coluna dentro da tabela

    @Column(length = 255)
    private String endereco;

    //criacao de coluna dentro da tabela

    @Column(length = 100)
    private String municipio;

    //criacao de coluna dentro da tabela

    @Column(length = 2)
    private String estado;

    //criacao de coluna dentro da tabela

    @Column(precision = 10, scale = 2)
    private Double areaPropriedade;

    //criacao de coluna dentro da tabela

    @Column(length = 100)
    private String tipoSemente;

    //criacao de coluna dentro da tabela

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    //criacao de coluna dentro da tabela



}
