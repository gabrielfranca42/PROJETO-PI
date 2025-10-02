package Repository;

import Model.AgricultorModel;
import ch.qos.logback.core.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgricultorRepository extends JpaRepository <AgricultorModel , Long> {
}
