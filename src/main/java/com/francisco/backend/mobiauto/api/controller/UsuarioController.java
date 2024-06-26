package com.francisco.backend.mobiauto.api.controller;

import com.francisco.backend.mobiauto.api.dto.request.UsuarioRemocaoRequest;
import com.francisco.backend.mobiauto.api.dto.request.UsuarioRequest;
import com.francisco.backend.mobiauto.api.dto.response.UsuarioResponse;
import com.francisco.backend.mobiauto.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth/usuarios")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasAnyRole('ROLE_PROPRIETARIO', 'ROLE_ADMIN')")
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
        UsuarioResponse usuarioResponse = usuarioService.cadastrar(usuarioRequest);
        return ResponseEntity.ok(usuarioResponse);
    }

    @DeleteMapping("/remover")
    public ResponseEntity<Void> remover(@RequestBody @Valid UsuarioRemocaoRequest usuarioRemocaoRequest) {
        usuarioService.remover(usuarioRemocaoRequest);
        return ResponseEntity.noContent().build();
    }

}
