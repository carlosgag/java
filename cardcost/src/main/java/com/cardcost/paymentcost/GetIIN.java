package com.cardcost.paymentcost;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetIIN implements Function<String, String> {
    @Override
    public String apply(String cardNumber) {
        if (cardNumber != null && cardNumber.length() >= 6) {
            return cardNumber.substring(0, 6);
        } else {
            return Strings.EMPTY;
        }
    }
}
