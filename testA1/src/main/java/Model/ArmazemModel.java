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
@Table (name = "Armazen")
public class ArmazemModel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long idArmazen;

    @Column (nullable = false, length = 180)
    private String nome;

    @Column (nullable = false, length = 4)
    private float metroCubicos;

    @Column (nullable = false, length = 18)
    private String Cnpj;

    @Column (nullable = false, length = 155)
    private String RegistroDeEntrada;


    @Column (nullable = false, length = 155)
    private String RegistroDeSaida;





}
