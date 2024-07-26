package com.cardcost.persistence;

import com.cardcost.persistence.data.ClearingCostData;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LocalDBTest implements WithAssertions {

    public static final String COUNTRY_ID = "countryId";
    public static final double COST = 10.11;
    public static final double UPDATED_COST = 12.0;
    private LocalDB localDB;

    @BeforeEach
    void setup() {
        localDB = new LocalDB();
    }

    @Test
    void testAllOperations() {
        ClearingCostData clearingCostData = new ClearingCostData();
        clearingCostData.setCountry(COUNTRY_ID);
        clearingCostData.setCost(COST);
        localDB.post(clearingCostData);
        final var result = localDB.get(COUNTRY_ID);
        assertThat(result.getCost()).isEqualTo(COST);

        result.setCost(UPDATED_COST);
        localDB.update(result);
        final var updatedResult = localDB.get(COUNTRY_ID);
        assertThat(updatedResult.getCost()).isEqualTo(UPDATED_COST);

        localDB.delete(COUNTRY_ID);
        final var deletedResult = localDB.get(COUNTRY_ID);
        assertThat(deletedResult).isNull();
    }
}