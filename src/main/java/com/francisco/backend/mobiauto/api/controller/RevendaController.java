package com.francisco.backend.mobiauto.api.controller;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.domain.service.RevendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/revendas")
@RequiredArgsConstructor
public class RevendaController {

    private final RevendaService revendaService;

    @PostMapping
    public ResponseEntity<RevendaResponse> criarRevenda(@Valid @RequestBody RevendaRequest revendaRequest) {
        RevendaResponse revendaResponse = revendaService.criarRevenda(revendaRequest);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(revendaResponse.getId()).toUri();
        return ResponseEntity.created(uri).body(revendaResponse);
    }

    @GetMapping("/{revendaId}")
    public ResponseEntity<RevendaResponse> buscarRevendaPorId(@PathVariable UUID revendaId) {
        RevendaResponse revendaResponse = revendaService.buscarRevendaPorId(revendaId);
        return ResponseEntity.ok().body(revendaResponse);
    }

    @GetMapping
    public ResponseEntity<Page<RevendaResponse>> buscarTodasAsRevendas(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "quantidadePorPagina", defaultValue = "10") int quantidadePorPagina) {

        Page<RevendaResponse> revendasEncontradas = revendaService.buscarTodasAsRevendas(pagina, quantidadePorPagina);
        return ResponseEntity.ok().body(revendasEncontradas);
    }

    @PutMapping("/{revendaId}")
    public ResponseEntity<RevendaResponse> atualizarRevenda(@PathVariable UUID revendaId,
                                                            @Valid @RequestBody RevendaRequest revendaRequest) {
        RevendaResponse revendaAtualizada = revendaService.atualizarRevenda(revendaId, revendaRequest);
        return ResponseEntity.ok().body(revendaAtualizada);
    }

}
