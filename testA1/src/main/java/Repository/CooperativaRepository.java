package Repository;

import Model.CooperativaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperativaRepository extends JpaRepository <CooperativaModel, Long> {
}
