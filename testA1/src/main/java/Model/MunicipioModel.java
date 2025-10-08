package Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "municipio")
public class MunicipioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false, length = 150)
    private String nome;

    @Column (nullable = false, length = 2)
    private String estado;

    @Column  (nullable = false, length = 18,unique = true)
    private  int Cnpj;


    @OneToMany(mappedBy = "municipio", fetch = FetchType.LAZY)
    private List<EnderecoModel> enderecoModel;






}
