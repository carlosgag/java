package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.entities.ClearingCost;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

@Service
public class GetCachedCost implements Function<String, ClearingCost> {

    private final HazelcastInstance hazelcastInstance;

    public GetCachedCost(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    private ConcurrentMap<String, ClearingCost> getCache() {
        return hazelcastInstance.getMap("map");
    }

    @Override
    public ClearingCost apply(String cardNumber) {
        return getCache().get(cardNumber);
    }
}
