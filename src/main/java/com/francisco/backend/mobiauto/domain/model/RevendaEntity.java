package com.francisco.backend.mobiauto.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class RevendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ
    @Column(unique = true, nullable = false)
    private String cnpj;

    @NotBlank(message = "Nome social é obrigatório")
    @Column(nullable = false)
    private String nomeSocial;

}
