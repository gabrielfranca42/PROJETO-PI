package DTO;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class EnderecoDto {

    private Long id;
    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private String pontoReferencia;
    private Long municipioId;
    private Long fornecedorId;

}
