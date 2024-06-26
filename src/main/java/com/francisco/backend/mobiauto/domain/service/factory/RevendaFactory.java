package com.francisco.backend.mobiauto.domain.service.factory;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.domain.model.RevendaEntity;

public class RevendaFactory {

    private RevendaFactory() {
    }

    public static RevendaEntity revendaRequestParaRevendaEntity(RevendaRequest revendaRequest) {
        return RevendaEntity.builder()
                .nomeSocial(revendaRequest.getNomeSocial())
                .cnpj(revendaRequest.getCnpj())
                .build();
    }

    public static RevendaResponse revendaEntityParaRevendaResponse(RevendaEntity revendaEntity) {
        return RevendaResponse.builder()
                .nomeSocial(revendaEntity.getNomeSocial())
                .cnpj(revendaEntity.getCnpj())
                .id(revendaEntity.getId())
                .build();
    }

}
