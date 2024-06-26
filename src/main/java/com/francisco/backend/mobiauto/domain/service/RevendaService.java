package com.francisco.backend.mobiauto.domain.service;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.CnpjJaExistenteException;
import com.francisco.backend.mobiauto.domain.model.RevendaEntity;
import com.francisco.backend.mobiauto.domain.repository.RevendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.francisco.backend.mobiauto.domain.service.factory.RevendaFactory.revendaEntityParaRevendaResponse;
import static com.francisco.backend.mobiauto.domain.service.factory.RevendaFactory.revendaRequestParaRevendaEntity;

@Service
@RequiredArgsConstructor
public class RevendaService {

    private RevendaRepository revendaRepository;

    public RevendaResponse criarRevenda(RevendaRequest revendaRequest) {
        checkExistenciaCnpj(revendaRequest);
        RevendaEntity revendaEntity = revendaRequestParaRevendaEntity(revendaRequest);
        revendaEntity = revendaRepository.save(revendaEntity);
        return revendaEntityParaRevendaResponse(revendaEntity);
    }

    private void checkExistenciaCnpj(RevendaRequest revendaRequest) {
        if (revendaRepository.findByCnpj(revendaRequest.getCnpj()).isPresent()) {
            throw new CnpjJaExistenteException("CNPJ já cadastrado");
        }
    }

}
