package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;

public interface DB {
    ClearingCostData get(String countryId);

    void post(ClearingCostData clearingCostData);

    void update(ClearingCostData clearingCostData);

    void delete(String countryId);
}
