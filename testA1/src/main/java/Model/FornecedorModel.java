package Model;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer id;

    @Column(nullable = false ,name = "cnpj", length = 19)
    private String cnpj;


    @Column( nullable = false ,name = "razaosocial", length = 155)
    private String razaoSocial;


    @Column( nullable = false ,name = "email", length = 155)
    private String email;


    @Column(  nullable = false,name = "endereco", length = 100)
    private String endereco;


    @Column( nullable = false,name = "estado", length = 2)
    private String estado;


    @Column(nullable = false,name = "telefone", length = 13)
    private String telefone;


}
