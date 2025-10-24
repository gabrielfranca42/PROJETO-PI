package testeA1.testA1.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "fornecimento")
public class FornecimentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private FornecedorModel fornecedor;

    @OneToMany(mappedBy = "fornecimento", fetch = FetchType.LAZY)
    private List<SementeModel> sementes;
}
