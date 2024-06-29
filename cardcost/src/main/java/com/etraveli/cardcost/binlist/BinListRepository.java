package com.etraveli.cardcost.binlist;

import com.etraveli.cardcost.binlist.entities.Response;
import com.etraveli.cardcost.binlist.exceptions.ExternalAPIException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Repository
@Slf4j
public class BinListRepository {

    public static final String ACCEPT_VERSION = "Accept-Version";
    public static final String GET_URL = "https://lookup.binlist.net/{card}";
    public static final String API_VERSION = "3";

    private final RestTemplate restTemplate;

    public BinListRepository() {
        this.restTemplate = new RestTemplate();
    }

    @CircuitBreaker(name = "binList", fallbackMethod = "fallback")
    public Response get(String card) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(ACCEPT_VERSION, API_VERSION);
        HttpEntity<Void> requestEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<Response> result = restTemplate.exchange(GET_URL, HttpMethod.GET, requestEntity, Response.class, card);
        log.debug(result.getStatusCode().toString());
        log.debug(Objects.requireNonNull(result.getBody()).toString());
        if(result.getStatusCode().is2xxSuccessful()) {
            return result.getBody();
        } else {
            HttpStatus httpStatus = HttpStatus.resolve(result.getStatusCode().value());
            throw new ExternalAPIException(httpStatus, "Error accessing external APIs");
        }
    }

    public ResponseEntity<Response> fallBack(String card, Exception exception) {
        final var msg = exception.getMessage();
        log.error("Circuit breaker exception querying card {}:{}", card, msg);
        return new ResponseEntity<>(Response.builder().build(), HttpStatus.OK);
    }
}
