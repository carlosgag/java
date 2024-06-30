package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.entities.ClearingCost;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetCachedCost implements Function<String, ClearingCost> {

    @Override
    public ClearingCost apply(String cardNumber) {
        return null;//getCache().get(cardNumber);
    }
}
