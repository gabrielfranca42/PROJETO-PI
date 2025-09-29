package Repository;

import Model.ArmazemModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmazemRepository extends JpaRepository <ArmazemModel, Long> {
}
