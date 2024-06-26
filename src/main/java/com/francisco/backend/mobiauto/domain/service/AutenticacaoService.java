package com.francisco.backend.mobiauto.domain.service;

import com.francisco.backend.mobiauto.api.dto.request.LoginUsuarioRequest;
import com.francisco.backend.mobiauto.domain.model.UsuarioModel;

public interface AutenticacaoService {

    UsuarioModel autenticar(LoginUsuarioRequest loginUsuarioRequest);

}
