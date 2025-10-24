package testeA1.testA1.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CooperativaDto {
    private Long idCooperativa;
    private String razaoSocial;
    private String cnpj;
    private String telefone;
    private String email;
    private String enderecoCompleto;
    private String municipio;
    private String estado;
    private String tipoAtuacao;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private String observacoes;
}
