package com.francisco.backend.mobiauto.api.controller;

import com.francisco.backend.mobiauto.api.dto.request.LoginUsuarioRequest;
import com.francisco.backend.mobiauto.api.dto.response.LoginResponse;
import com.francisco.backend.mobiauto.domain.model.UsuarioModel;
import com.francisco.backend.mobiauto.domain.service.AutenticacaoService;
import com.francisco.backend.mobiauto.infra.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AutenticacaoController {

    private final JwtService jwtService;
    private final AutenticacaoService autenticacaoService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> autenticar(@RequestBody LoginUsuarioRequest loginUsuarioRequest) {
        UsuarioModel authenticatedUser = autenticacaoService.autenticar(loginUsuarioRequest);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse
                .builder()
                .token(jwtToken)
                .expiraEm(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }

}
