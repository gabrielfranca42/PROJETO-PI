package testeA1.testA1.DTO;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SementeDto {


    private Long id;
    private String nome;
    private Integer validade;
    private String especie;
    private Long fornecimentoId;
    private List<Long> estoqueIds;
    private List<Long> loteIds;
    private List<Long> loteControleIds;


}
