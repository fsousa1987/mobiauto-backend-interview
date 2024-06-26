package com.francisco.backend.mobiauto.api.exceptionhandler;

import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.CnpjJaExistenteException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {CnpjJaExistenteException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(CnpjJaExistenteException cnpjJaExistenteException) {
        log.error(cnpjJaExistenteException.getMessage(), cnpjJaExistenteException);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(cnpjJaExistenteException.getMessage())
                .build();
    }

}
