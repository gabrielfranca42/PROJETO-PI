package testeA1.testA1.Repository;

import testeA1.testA1.Model.SementeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementeRepository  extends JpaRepository <SementeModel , Long> {
}
