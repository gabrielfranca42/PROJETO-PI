package Model;


import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
//apenas criacao de entidade no banco de dados e nome da tabela
@Entity
@Table(name = "Semetes")

public class SementeModel {
//id e geracao de id automatico pelo mysql o tipo de estrategia e inclusive autoincremente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true , nullable = false)
    private String nomeSemente;

    //apena coluna no banco de dados

    @Column( nullable = false)
    @Min(1)
    @Max(3)
    private int prioridadeSemente;

    //apenas uma coluna no banco de dados que obriga um valor minimo e um valor maximo int

    @Column( nullable = false)
    @DecimalMin(value = "1.0", inclusive = true)
    private float quantidadeKgSemente;

    //apenas criacao de uma coluna no banco de dados que obriga um valor minimo float


    @Column(nullable = false)
    @DecimalMin(value = "0.1")
    private float precoKgSementes;

    //apenas criacao de uma coluna no banco de dados que obriga um valor minimo float


    public SementeModel() {
    }



}
