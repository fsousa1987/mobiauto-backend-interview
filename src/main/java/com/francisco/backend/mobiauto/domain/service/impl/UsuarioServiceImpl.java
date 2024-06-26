package com.francisco.backend.mobiauto.domain.service.impl;

import com.francisco.backend.mobiauto.api.dto.request.UsuarioRemocaoRequest;
import com.francisco.backend.mobiauto.api.dto.request.UsuarioRequest;
import com.francisco.backend.mobiauto.api.dto.response.UsuarioResponse;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.UsuarioJaExistenteException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.UsuarioNaoEncontradoException;
import com.francisco.backend.mobiauto.domain.model.UsuarioModel;
import com.francisco.backend.mobiauto.domain.repository.UsuarioRepository;
import com.francisco.backend.mobiauto.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

import static com.francisco.backend.mobiauto.domain.service.factory.UsuarioFactory.usuarioModelParaUsuarioResponse;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse cadastrar(UsuarioRequest usuarioRequest) {
        AtomicReference<UsuarioModel> usuario = new AtomicReference<>();
        usuarioRepository.findByEmail(usuarioRequest.getEmail()).ifPresentOrElse(email -> {
            throw new UsuarioJaExistenteException("Usuário já existe");
        }, () -> usuario.set(construirUsuarioModel(usuarioRequest)));

        UsuarioModel usuarioModel = usuarioRepository.save(usuario.get());

        return usuarioModelParaUsuarioResponse(usuarioModel);
    }

    @Override
    public void remover(UsuarioRemocaoRequest usuarioRemocaoRequest) {
        usuarioRepository.findByEmail(usuarioRemocaoRequest.getEmail())
                .ifPresentOrElse(usuarioRepository::delete, () -> {
                    throw new UsuarioNaoEncontradoException("Usuário não encontrado");
                });
    }

    private UsuarioModel construirUsuarioModel(UsuarioRequest usuarioRequest) {
        return UsuarioModel
                .builder()
                .fullName(usuarioRequest.getNome())
                .email(usuarioRequest.getEmail())
                .password(passwordEncoder.encode(usuarioRequest.getSenha()))
                .build();
    }

}
