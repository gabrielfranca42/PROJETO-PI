package testeA1.testA1.Repository;

import testeA1.testA1.Model.EnderecoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository <EnderecoModel ,Long> {
}
