package DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MunicipioDto {
    private long id;
    private String nome;
    private String estado;
    private int cnpj;

}
