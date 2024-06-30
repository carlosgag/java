package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LocalDB extends HashMap<String, ClearingCostData> implements DB {
    @Override
    public ClearingCostData get(String countryId) {
        return super.get(countryId);
    }

    @Override
    public void post(ClearingCostData clearingCostData) {
        this.put(clearingCostData.getCountry(), clearingCostData);
    }

    @Override
    public void update(ClearingCostData clearingCostData) {
        this.post(clearingCostData);
    }

    @Override
    public void delete(String countryId) {
        this.remove(countryId);
    }


}
