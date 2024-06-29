package com.etraveli.cardcost.config;

import com.etraveli.cardcost.binlist.exceptions.ExternalAPIException;
import com.etraveli.cardcost.entities.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(ExternalAPIException.class)
    public ResponseEntity<ErrorMessage> externalAPIHandler(ExternalAPIException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .status(exception.getHttpStatus())
                .msg(exception.getMessage())
                .build(), exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validation(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .msg("Validation error: " +
                        Objects.requireNonNull(exception.getBindingResult().getFieldError("cardNumber")).getDefaultMessage())
                .description(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generic(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .msg("Non identified error: " + exception.getClass().getSimpleName())
                .status(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(exception.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
