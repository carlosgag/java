package com.cardcost.paymentcost;

import com.cardcost.entities.ClearingCost;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class GetCachedCost implements Function<String, ClearingCost> {

    @Override
    public ClearingCost apply(String cardNumber) {
        // TODO fix hazelcast issues and uncomment logic for retrieving cached info
        return null;//getCache().get(cardNumber);
    }
}
