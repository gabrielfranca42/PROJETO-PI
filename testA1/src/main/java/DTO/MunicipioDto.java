package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MunicipioDto {
    private Long idMunicipio;
    private String nome;
    private String estado;
    private int cnpj;

}
