package com.francisco.backend.mobiauto.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.UUID;

@Entity
@Data
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
