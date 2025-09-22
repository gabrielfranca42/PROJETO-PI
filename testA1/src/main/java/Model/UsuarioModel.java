package Model;


import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    private String username;


    @Column(nullable = false)
    private String password;


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


