package com.francisco.backend.mobiauto.domain.service.impl;

import com.francisco.backend.mobiauto.api.dto.request.LoginUsuarioRequest;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.UsuarioNaoEncontradoException;
import com.francisco.backend.mobiauto.domain.model.UsuarioModel;
import com.francisco.backend.mobiauto.domain.repository.UsuarioRepository;
import com.francisco.backend.mobiauto.domain.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AutenticacaoServiceImpl implements AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public UsuarioModel autenticar(LoginUsuarioRequest loginUsuarioRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginUsuarioRequest.getEmail(),
                        loginUsuarioRequest.getSenha()));

        return usuarioRepository.findByEmail(loginUsuarioRequest.getEmail())
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Usuário não encontrado"));
    }

}
