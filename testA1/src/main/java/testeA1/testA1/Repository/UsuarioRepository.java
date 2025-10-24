package testeA1.testA1.Repository;

import testeA1.testA1.Model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <UsuarioModel , Long> {

    Optional<UsuarioModel> findByUsername(String username);

}
