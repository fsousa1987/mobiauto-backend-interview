package com.francisco.backend.mobiauto.api.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class UsuarioResponse {

    private UUID id;

    private String nome;

    private String email;

}
