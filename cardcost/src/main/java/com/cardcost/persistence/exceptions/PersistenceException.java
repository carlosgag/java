package com.cardcost.persistence.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PersistenceException extends RuntimeException {
    private String msg;

    public PersistenceException(String msg, Exception e) {
        this.msg = msg;
    }
}
