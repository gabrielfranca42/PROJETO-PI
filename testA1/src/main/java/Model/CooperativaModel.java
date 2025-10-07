package Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cooperativa")
public class CooperativaModel {

    @Column(nullable = false, length = 150)
    private String razaoSocial;

    @Column(nullable = false, unique = true, length = 19)
    private String cnpj;

    @Column(length = 15)
    private String telefone;

    @Column(length = 120)
    private String email;

    @Column(length = 155)
    private String enderecoCompleto;

    @Column(length = 100)
    private String municipio;

    @Column(length = 2)
    private String estado;

    // Data de registro no sistema
    @Column(nullable = false)
    private LocalDateTime dataCadastro = LocalDateTime.now();

    // Status da cooperativa no sistema (ativa/inativa)
    @Column(nullable = false)
    private Boolean ativo = true;

    // Observações administrativas
    @Column(length = 255)
    private String observacoes;

}
