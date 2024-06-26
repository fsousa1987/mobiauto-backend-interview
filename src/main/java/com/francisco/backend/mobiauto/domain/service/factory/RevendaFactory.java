package com.francisco.backend.mobiauto.domain.service.factory;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.domain.model.RevendaModel;

public class RevendaFactory {

    private RevendaFactory() {
    }

    public static RevendaModel revendaRequestParaRevendaModel(RevendaRequest revendaRequest) {
        return RevendaModel.builder()
                .nomeSocial(revendaRequest.getNomeSocial())
                .cnpj(revendaRequest.getCnpj())
                .build();
    }

    public static RevendaResponse revendaModelParaRevendaResponse(RevendaModel revendaModel) {
        return RevendaResponse.builder()
                .nomeSocial(revendaModel.getNomeSocial())
                .cnpj(revendaModel.getCnpj())
                .id(revendaModel.getId())
                .build();
    }

}
