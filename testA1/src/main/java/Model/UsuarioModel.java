package Model;


import jakarta.persistence.*;


// apenas criacao da entidade no banco de dados e nome da tabela

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

    //apenas criacao de coluna no banco de dados

    //aqui abaixo apenas criacao automatica de construtores vazios  e cheios mais os gets and sets

    public UsuarioModel() {}


    public UsuarioModel(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public UsuarioModel(String username, String password, Long id){
        this.id = id;
        this.password = password;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}


