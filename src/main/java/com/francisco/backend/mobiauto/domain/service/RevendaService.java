package com.francisco.backend.mobiauto.domain.service;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;

public interface RevendaService {

    RevendaResponse criarRevenda(RevendaRequest revendaRequest);

}
