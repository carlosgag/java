package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
import com.etraveli.cardcost.persistence.H2DB;
import com.etraveli.cardcost.persistence.data.ClearingCostData;
import org.springframework.stereotype.Repository;

@Repository
public class CostMatrixRepository {

    private final H2DB db;

    public CostMatrixRepository(H2DB db) {
        this.db = db;
    }

    public ClearingCost get(String country) {
        ClearingCostData cost = db.get(country);
        return ClearingCost.builder()
                .cost(cost.getCost())
                .country(cost.getCountry())
                .build();
    }

    public void post(ClearingCost clearingCost) {
        db.post(new ClearingCostData(clearingCost.getCountry(), clearingCost.getCost()));
    }

    public void update(ClearingCost clearingCost) {
        db.update(new ClearingCostData(clearingCost.getCountry(), clearingCost.getCost()));
    }

    public void delete(String countryId) {
        db.delete(countryId);
    }
}
