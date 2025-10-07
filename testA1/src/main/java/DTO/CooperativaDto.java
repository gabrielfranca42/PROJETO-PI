package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
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
