package com.francisco.backend.mobiauto.api.exceptionhandler;

import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.CnpjJaExistenteException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.RevendaNaoEncontradaException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.UsuarioJaExistenteException;
import com.francisco.backend.mobiauto.api.exceptionhandler.exceptions.UsuarioNaoEncontradoException;
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

    @ResponseBody
    @ExceptionHandler(value = {RevendaNaoEncontradaException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(RevendaNaoEncontradaException revendaNaoEncontradaException) {
        log.error(revendaNaoEncontradaException.getMessage(), revendaNaoEncontradaException);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(revendaNaoEncontradaException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {UsuarioJaExistenteException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDTO handleException(UsuarioJaExistenteException usuarioJaExistenteException) {
        log.error(usuarioJaExistenteException.getMessage(), usuarioJaExistenteException);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.CONFLICT.value()))
                .error(HttpStatus.CONFLICT.getReasonPhrase())
                .message(usuarioJaExistenteException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {UsuarioNaoEncontradoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleException(UsuarioNaoEncontradoException usuarioNaoEncontradoException) {
        log.error(usuarioNaoEncontradoException.getMessage(), usuarioNaoEncontradoException);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .error(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(usuarioNaoEncontradoException.getMessage())
                .build();
    }

}
