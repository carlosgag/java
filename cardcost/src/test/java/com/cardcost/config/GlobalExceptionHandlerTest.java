package com.cardcost.config;

import com.cardcost.binlist.exceptions.ExternalAPIException;
import com.cardcost.persistence.exceptions.PersistenceException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest implements WithAssertions {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setup() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void testException() {
        final var result = globalExceptionHandler.generic(new Exception());
        assertThat(Objects.requireNonNull(result.getStatusCode()))
                .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void testMethodArgumentNotValidException() {
        //  GIVEN
        MethodArgumentNotValidException methodArgumentNotValidException = Mockito
                .mock(MethodArgumentNotValidException.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        FieldError fieldError = Mockito.mock(FieldError.class);
        when(bindingResult.getFieldError(any())).thenReturn(fieldError);
        when(methodArgumentNotValidException.getBindingResult())
                .thenReturn(bindingResult);
        //  WHEN
        final var result = globalExceptionHandler.validation(methodArgumentNotValidException);
        //  THEN
        assertThat(Objects.requireNonNull(result.getStatusCode()))
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testExternalAPIException() {
        //  GIVEN
        ExternalAPIException externalAPIException = Mockito
                .mock(ExternalAPIException.class);
        when(externalAPIException.getHttpStatus())
                .thenReturn(HttpStatus.BAD_REQUEST);
        //  WHEN
        final var result = globalExceptionHandler.externalAPIHandler(externalAPIException);

        //  THEN
        assertThat(Objects.requireNonNull(result.getStatusCode()))
                .isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void testPersistenceException() {
        //  GIVEN
        PersistenceException persistenceException = Mockito.mock(PersistenceException.class);
        //  WHEN
        final var result = globalExceptionHandler.persistenceExceptionHandler(persistenceException);
        //  THEN
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.SERVICE_UNAVAILABLE);
    }
}