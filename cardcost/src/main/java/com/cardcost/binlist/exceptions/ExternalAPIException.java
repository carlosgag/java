package com.cardcost.binlist.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class ExternalAPIException extends RuntimeException {
    private HttpStatus httpStatus;

    public ExternalAPIException(HttpStatus httpStatus, String msg) {
        super(msg);
        this.httpStatus = httpStatus;
    }
}
