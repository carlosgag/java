package com.etraveli.cardcost.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Slf4j
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorMessage> client(HttpClientErrorException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder()
                .status(exception.getStatusCode())
                .msg(exception.getMessage())
                .build(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generic(Exception exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(ErrorMessage.builder().msg("Non identified error").build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
