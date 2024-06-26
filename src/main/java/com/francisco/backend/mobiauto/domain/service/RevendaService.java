package com.francisco.backend.mobiauto.domain.service;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface RevendaService {

    RevendaResponse criarRevenda(RevendaRequest revendaRequest);

    RevendaResponse buscarRevendaPorId(UUID revendaId);

    Page<RevendaResponse> buscarTodasAsRevendas(int pagina, int quantidadePorPagina);

}
