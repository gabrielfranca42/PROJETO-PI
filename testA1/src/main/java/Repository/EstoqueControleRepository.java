package Repository;

import Model.EstoqueControleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueControleRepository extends JpaRepository <EstoqueControleModel, Long> {
}
