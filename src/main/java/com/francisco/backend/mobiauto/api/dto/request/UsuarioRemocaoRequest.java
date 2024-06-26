package com.francisco.backend.mobiauto.api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsuarioRemocaoRequest {

    @NotNull(message = "O campo email precisa ser preenchido")
    @Email(message = "O campo email não está válido")
    private String email;

}
