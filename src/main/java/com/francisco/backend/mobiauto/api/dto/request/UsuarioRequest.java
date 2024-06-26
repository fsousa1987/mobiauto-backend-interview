package com.francisco.backend.mobiauto.api.dto.request;

import com.francisco.backend.mobiauto.api.enums.UsuarioPerfil;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UsuarioRequest {

    @NotNull(message = "O campo nome precisa ser preenchido")
    private String nome;

    @NotNull(message = "O campo email precisa ser preenchido")
    @Email(message = "O campo email não está válido")
    private String email;

    @NotNull(message = "O campo senha precisa ser preenchido")
    private String senha;

    @NotNull(message = "O campo usuário perfil precisa ser preenchido")
    private UsuarioPerfil perfil;

}
