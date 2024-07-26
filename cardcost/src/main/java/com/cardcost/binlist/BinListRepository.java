package com.cardcost.binlist;

import com.cardcost.binlist.entities.Response;
import com.cardcost.binlist.exceptions.ExternalAPIException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Repository
@Slf4j
public class BinListRepository {

    public static final String ACCEPT_VERSION = "Accept-Version";
    public static final String GET_URL = "https://lookup.binlist.net/{card}";
    public static final String API_VERSION = "3";
    public static final String ERROR_ACCESSING_EXTERNAL_API = "Error accessing external API";


    private final RestTemplate restTemplate;

    public BinListRepository() {
        this.restTemplate = new RestTemplate();
    }

    @CircuitBreaker(name = "binList", fallbackMethod = "fallBack")
    @Retry(name = "binList", fallbackMethod = "fallBack")
    public Response get(String card) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ACCEPT_VERSION, API_VERSION);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        try {
            ResponseEntity<Response> result = restTemplate.exchange(GET_URL, HttpMethod.GET, requestEntity, Response.class, card);
            log.debug(result.getStatusCode().toString());
            log.debug(Objects.requireNonNull(result.getBody()).toString());
            if (result.getStatusCode().is2xxSuccessful()) {
                return result.getBody();
            } else {
                HttpStatus httpStatus = HttpStatus.resolve(result.getStatusCode().value());
                throw new ExternalAPIException(httpStatus, ERROR_ACCESSING_EXTERNAL_API);
            }
        } catch (HttpClientErrorException e) {
            log.error(e.getMessage());
            HttpStatus httpStatus = HttpStatus.resolve(e.getStatusCode().value());
            throw new ExternalAPIException(httpStatus, ERROR_ACCESSING_EXTERNAL_API);
        }
    }

    private Response fallBack(String card, Exception exception) {
        //  TODO implement different fallbacks for each operation, adding retries inside de fallback or message queue
        final var msg = exception.getMessage();
        log.error("Circuit breaker exception querying card {}:{}", card, msg);
        throw new ExternalAPIException(HttpStatus.FAILED_DEPENDENCY, ERROR_ACCESSING_EXTERNAL_API);
    }
}
