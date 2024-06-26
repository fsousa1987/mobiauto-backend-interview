package com.francisco.backend.mobiauto.api.exceptionhandler;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorDTO(LocalDateTime timestamp, String status, String error, String message) {

}
