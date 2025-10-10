package DTO;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MunicipioDto {
    private Integer id;
    private String nome;
    private String estado;
    private Integer cnpj;
    private List<Long> enderecoIds;

}
