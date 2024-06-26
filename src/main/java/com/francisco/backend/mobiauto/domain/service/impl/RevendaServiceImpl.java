package com.francisco.backend.mobiauto.domain.service.impl;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.CnpjJaExistenteException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.RevendaNaoEncontradaException;
import com.francisco.backend.mobiauto.domain.model.RevendaModel;
import com.francisco.backend.mobiauto.domain.repository.RevendaRepository;
import com.francisco.backend.mobiauto.domain.service.RevendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.francisco.backend.mobiauto.domain.service.factory.RevendaFactory.revendaModelParaRevendaResponse;
import static com.francisco.backend.mobiauto.domain.service.factory.RevendaFactory.revendaRequestParaRevendaModel;

@Service
@RequiredArgsConstructor
public class RevendaServiceImpl implements RevendaService {

    private final RevendaRepository revendaRepository;

    @Override
    @Transactional
    public RevendaResponse criarRevenda(RevendaRequest revendaRequest) {
        checkExistenciaCnpj(revendaRequest);
        RevendaModel revendaModel = revendaRequestParaRevendaModel(revendaRequest);
        revendaModel = revendaRepository.save(revendaModel);
        return revendaModelParaRevendaResponse(revendaModel);
    }

    @Override
    @Transactional(readOnly = true)
    public RevendaResponse buscarRevendaPorId(UUID id) {
        RevendaModel revendaModel = revendaRepository.findById(id).orElseThrow(()
                -> new RevendaNaoEncontradaException("Revenda não foi encontrada"));
        return revendaModelParaRevendaResponse(revendaModel);
    }

    private void checkExistenciaCnpj(RevendaRequest revendaRequest) {
        if (revendaRepository.findByCnpj(revendaRequest.getCnpj()).isPresent()) {
            throw new CnpjJaExistenteException("CNPJ já cadastrado");
        }
    }

}
