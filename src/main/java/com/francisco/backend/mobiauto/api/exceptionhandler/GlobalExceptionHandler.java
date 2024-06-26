package com.francisco.backend.mobiauto.api.exceptionhandler;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String INVALID_PARAMETER_REQUEST = "Invalid request, please check the parameters and try again";

    @ResponseBody
    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(IllegalArgumentException illegalArgumentException) {
        log.error(illegalArgumentException.getMessage(), illegalArgumentException);

        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(INVALID_PARAMETER_REQUEST)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {PropertyReferenceException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(PropertyReferenceException propertyReferenceException) {
        log.error(propertyReferenceException.getMessage(), propertyReferenceException);

        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(propertyReferenceException.getMessage())
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MethodArgumentNotValidException methodArgumentNotValidException) {
        log.error(methodArgumentNotValidException.getMessage(), methodArgumentNotValidException);

        String getErrorMessage = methodArgumentNotValidException
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(getErrorMessage)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("Unexpected error!")
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
        log.error(methodArgumentTypeMismatchException.getMessage(), methodArgumentTypeMismatchException);
        return ErrorDTO
                .builder()
                .timestamp(LocalDateTime.now())
                .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(INVALID_PARAMETER_REQUEST)
                .build();
    }

    @ResponseBody
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleException(ValidationException validationException) {
        ErrorDTO errorDTO;
        if (validationException instanceof ConstraintViolationException) {
            String violations = extractViolationsFromException((ConstraintViolationException) validationException);
            log.error(violations, validationException);
            errorDTO = ErrorDTO
                    .builder()
                    .timestamp(LocalDateTime.now())
                    .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(violations)
                    .build();
        } else {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            errorDTO = ErrorDTO
                    .builder()
                    .timestamp(LocalDateTime.now())
                    .status(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                    .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message(exceptionMessage)
                    .build();
        }
        return errorDTO;
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

}
