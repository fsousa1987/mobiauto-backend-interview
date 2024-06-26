package com.francisco.backend.mobiauto.api.controller;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.domain.service.RevendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/revendas")
@RequiredArgsConstructor
public class RevendaController {

    private RevendaService revendaService;

    @PostMapping
    public ResponseEntity<RevendaResponse> criarRevenda(@Valid @RequestBody RevendaRequest revendaRequest) {
        RevendaResponse revendaResponse = revendaService.criarRevenda(revendaRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(revendaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(revendaResponse);
    }

}
