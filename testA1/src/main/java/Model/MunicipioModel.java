package Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "municipio")
public class MunicipioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMunicipio;

    @Column (nullable = false, length = 150)
    private String nome;

    @Column (nullable = false, length = 2)
    private String estado;

    @Column  (nullable = false, length = 18,unique = true)
    private  int Cnpj;





}
