package DTO;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDto {
    private Long id;
    private String username;
    private String password;

}
