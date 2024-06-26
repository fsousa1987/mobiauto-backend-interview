package com.francisco.backend.mobiauto.domain.service.factory;

import com.francisco.backend.mobiauto.api.dto.response.UsuarioResponse;
import com.francisco.backend.mobiauto.domain.model.UsuarioModel;

public class UsuarioFactory {

    private UsuarioFactory() {
    }

    public static UsuarioResponse usuarioModelParaUsuarioResponse(UsuarioModel usuarioModel) {
        return UsuarioResponse.builder()
                .nome(usuarioModel.getUsername())
                .email(usuarioModel.getEmail())
                .id(usuarioModel.getId())
                .build();
    }

}
