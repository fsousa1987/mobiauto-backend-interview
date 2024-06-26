package com.francisco.backend.mobiauto.domain.service;

import com.francisco.backend.mobiauto.api.dto.request.UsuarioRemocaoRequest;
import com.francisco.backend.mobiauto.api.dto.request.UsuarioRequest;
import com.francisco.backend.mobiauto.api.dto.response.UsuarioResponse;

public interface UsuarioService {

    UsuarioResponse cadastrar(UsuarioRequest usuarioRequest);

    void remover(UsuarioRemocaoRequest usuarioRemocaoRequest);

}
