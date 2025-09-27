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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgricultor;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    private LocalDate dataNascimento;

    @Column(length = 15)
    private String telefone;

    @Column(length = 120)
    private String email;

    @Column(length = 255)
    private String endereco;

    @Column(length = 100)
    private String municipio;

    @Column(length = 2)
    private String estado;

    @Column(precision = 10, scale = 2)
    private Double areaPropriedade;

    @Column(length = 100)
    private String tipoSemente;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

}
