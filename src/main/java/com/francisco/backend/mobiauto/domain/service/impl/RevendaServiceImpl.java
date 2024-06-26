package com.francisco.backend.mobiauto.domain.service.impl;

import com.francisco.backend.mobiauto.api.dto.request.RevendaRequest;
import com.francisco.backend.mobiauto.api.dto.response.RevendaResponse;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.CnpjJaExistenteException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.RevendaNaoEncontradaException;
import com.francisco.backend.mobiauto.domain.model.RevendaModel;
import com.francisco.backend.mobiauto.domain.repository.RevendaRepository;
import com.francisco.backend.mobiauto.domain.service.RevendaService;
import com.francisco.backend.mobiauto.domain.service.factory.RevendaFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        RevendaModel revendaModel = revendaRequestParaRevendaModel(revendaRequest, null);
        revendaModel = revendaRepository.save(revendaModel);
        return revendaModelParaRevendaResponse(revendaModel);
    }

    @Override
    @Transactional(readOnly = true)
    public RevendaResponse buscarRevendaPorId(UUID revendaId) {
        RevendaModel revendaModel = checkSeRevendaExiste(revendaId);
        return revendaModelParaRevendaResponse(revendaModel);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<RevendaResponse> buscarTodasAsRevendas(int pagina, int quantidadePorPagina) {
        Pageable pageable = PageRequest.of(pagina, quantidadePorPagina);
        Page<RevendaModel> revendasEncontradas = revendaRepository.findAll(pageable);
        return revendasEncontradas.map(RevendaFactory::revendaModelParaRevendaResponse);
    }

    @Override
    @Transactional
    public RevendaResponse atualizarRevenda(UUID revendaId, RevendaRequest revendaRequest) {
        RevendaModel revendaModel = checkSeRevendaExiste(revendaId);
        if (!revendaModel.getCnpj().equals(revendaRequest.getCnpj())) {
            checkExistenciaCnpj(revendaRequest);
        }
        return getRevendaResponse(revendaId, revendaRequest);
    }

    @Override
    @Transactional
    public void removerRevenda(UUID revendaId) {
        RevendaModel revendaModel = checkSeRevendaExiste(revendaId);
        revendaRepository.delete(revendaModel);
    }

    private RevendaResponse getRevendaResponse(UUID revendaId, RevendaRequest revendaRequest) {
        RevendaModel revendaModel;
        revendaModel = revendaRequestParaRevendaModel(revendaRequest, revendaId);
        revendaModel = revendaRepository.save(revendaModel);
        return revendaModelParaRevendaResponse(revendaModel);
    }

    private void checkExistenciaCnpj(RevendaRequest revendaRequest) {
        if (revendaRepository.findByCnpj(revendaRequest.getCnpj()).isPresent()) {
            throw new CnpjJaExistenteException("CNPJ já cadastrado");
        }
    }

    private RevendaModel checkSeRevendaExiste(UUID revendaId) {
        return revendaRepository.findById(revendaId).orElseThrow(()
                -> new RevendaNaoEncontradaException("Revenda não foi encontrada"));
    }

}
