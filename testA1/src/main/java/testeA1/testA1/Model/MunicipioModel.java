package testeA1.testA1.Model;// package: testeA1.testA1.Model

import jakarta.persistence.*;
import lombok.*;
import testeA1.testA1.Model.EnderecoModel;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "municipio")
public class MunicipioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, length = 2)
    private String estado;

    @Column(nullable = false, length = 18, unique = true)
    private int Cnpj;

    // ALTERAÇÃO: mappedBy estava correto, nenhuma alteração aqui
    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<EnderecoModel> enderecoModel;
}

