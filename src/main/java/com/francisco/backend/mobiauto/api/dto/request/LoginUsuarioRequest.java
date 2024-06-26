package com.francisco.backend.mobiauto.api.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUsuarioRequest {

    @NotNull(message = "Preencha o campo email")
    private String email;

    @NotNull(message = "Preencha o campo senha")
    private String senha;

}
