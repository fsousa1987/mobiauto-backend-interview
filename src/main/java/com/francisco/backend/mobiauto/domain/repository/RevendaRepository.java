package com.francisco.backend.mobiauto.domain.repository;

import com.francisco.backend.mobiauto.domain.model.RevendaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RevendaRepository extends JpaRepository<RevendaModel, UUID> {

    Optional<RevendaModel> findByCnpj(String cnpj);

}
