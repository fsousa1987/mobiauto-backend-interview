package com.francisco.backend.mobiauto.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

@Data
public class RevendaRequest {

    @NotBlank(message = "CNPJ é obrigatório")
    @CNPJ
    private String cnpj;

    @NotBlank(message = "Nome social é obrigatório")
    @Size(max = 120, message = "Nome social deve ter no máximo 120 caracteres")
    private String nomeSocial;

}
