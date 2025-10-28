package testeA1.testA1.DTO;

import lombok.*;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FornecedorDto {

    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pontoReferencia;
    private List<Long> fornecimentoIds;
    private List<Long> loteControleIds;

}
