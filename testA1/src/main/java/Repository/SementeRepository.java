package Repository;

import ch.qos.logback.core.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SementeRepository extends JpaRepository <Model, Long> {
}
