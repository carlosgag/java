package com.etraveli.cardcost.config;

import com.etraveli.cardcost.binlist.exceptions.ExternalAPIException;
import com.etraveli.cardcost.entities.ClearingCost;
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
    public ResponseEntity<ClearingCost> externalAPIHandler(ExternalAPIException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> validation(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .msg(String.format("Validation error: %s",
                        Objects.requireNonNull(exception.getBindingResult().getFieldError("cardNumber")).getDefaultMessage()))
                .description(exception.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generic(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .msg(String.format("Non identified error: %s", exception.getClass().getSimpleName()))
                .status(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description(exception.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
