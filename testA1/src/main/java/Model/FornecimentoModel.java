package Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "fornecimento")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
