package com.francisco.backend.mobiauto.api.exceptionhandler.exceptions;

public class UsuarioJaExistenteException extends RuntimeException {

    public UsuarioJaExistenteException(String message) {
        super(message);
    }

}
