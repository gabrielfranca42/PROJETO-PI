package Repository;

import Model.MunicipioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MunicipioRepository extends JpaRepository<MunicipioModel, Long> {
}
