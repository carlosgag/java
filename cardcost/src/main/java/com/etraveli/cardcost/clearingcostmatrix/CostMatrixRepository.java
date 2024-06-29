package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
import org.springframework.stereotype.Repository;

@Repository
public class CostMatrixRepository {

    private final DB db;

    public CostMatrixRepository(DB db) {
        this.db = db;
    }

    public ClearingCost get(String country) {
        Double cost = db.get(country);
        return cost != null ? ClearingCost.builder()
                .cost(cost)
                .country(country)
                .build() : null;
    }

    public void post(ClearingCost clearingCost) {
        db.put(clearingCost.getCountry(), clearingCost.getCost());
    }

    public void put(ClearingCost clearingCost) {
        db.replace(clearingCost.getCountry(), clearingCost.getCost());
    }

    public void delete(String countryId) {
        db.remove(countryId);
    }
}
