package com.francisco.backend.mobiauto.api.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RevendaResponse {

    private UUID id;

    private String cnpj;

    private String nomeSocial;

}
