package testeA1.testA1.DTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class FornecimentoDto {

    private Long id;
    private Long fornecedorId;
    private List<Long> sementeIds;
}
