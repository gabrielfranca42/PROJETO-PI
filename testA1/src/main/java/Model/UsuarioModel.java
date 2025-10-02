package Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// apenas criacao da entidade no banco de dados e nome da tabela
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // apenas geracao de id e sua estretegia que no caso e autoincremente


    @Column(unique = true, nullable = false)
    private String username;

    //apenas criacao de coluna no banco de dados


    @Column(nullable = false)
    private String password;


}

    //apenas criacao de coluna no banco de dados

    //aqui abaixo apenas criacao automatica de construtores vazios  e cheios mais os gets and sets



