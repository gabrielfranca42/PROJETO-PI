package Repository;

import Model.LoteControleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteControleRepository extends JpaRepository <LoteControleModel, Long> {
}
