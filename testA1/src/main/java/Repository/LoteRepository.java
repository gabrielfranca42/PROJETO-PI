package Repository;

import Model.LoteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteRepository extends JpaRepository<LoteModel, Long> {
}
