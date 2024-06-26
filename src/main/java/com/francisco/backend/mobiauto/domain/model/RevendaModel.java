package com.francisco.backend.mobiauto.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "revenda")
public class RevendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ
    @Column(unique = true, nullable = false)
    private String cnpj;

    @NotBlank(message = "Nome social é obrigatório")
    @Size(max = 120, message = "Nome social deve ter no máximo 120 caracteres")
    @Column(name = "nome_social", nullable = false, length = 120)
    private String nomeSocial;

}
