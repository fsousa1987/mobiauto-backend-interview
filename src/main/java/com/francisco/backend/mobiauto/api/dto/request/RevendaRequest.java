package com.francisco.backend.mobiauto.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class RevendaRequest {

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "Nome social é obrigatório")
    private String nomeSocial;

}
