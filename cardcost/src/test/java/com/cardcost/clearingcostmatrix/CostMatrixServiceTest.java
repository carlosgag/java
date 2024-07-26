package com.cardcost.clearingcostmatrix;

import com.cardcost.entities.ClearingCost;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CostMatrixServiceTest implements WithAssertions {
    public static final String COUNTRY_ID = "countryId";
    public static final double COST = 10.0;
    @Mock
    private CostMatrixRepository costMatrixRepository;

    private CostMatrixService costMatrixService;

    @BeforeEach
    void setup() {
        costMatrixService = new CostMatrixService(costMatrixRepository);
    }

    @Test
    void testGet() {
        ClearingCost clearingCost = Mockito.mock(ClearingCost.class);
        when(clearingCost.getCost())
                .thenReturn(COST);
        when(costMatrixRepository.get(COUNTRY_ID))
                .thenReturn(clearingCost);
        final var result = costMatrixService.get(COUNTRY_ID);
        assertThat(result.getCost()).isEqualTo(COST);
    }

    @Test
    void testPost() {
        ClearingCost clearingCost = Mockito.mock(ClearingCost.class);
        costMatrixService.post(clearingCost);
        verify(costMatrixRepository).post(clearingCost);
    }

    @Test
    void testDelete() {
        costMatrixService.delete(COUNTRY_ID);
        verify(costMatrixRepository).delete(COUNTRY_ID);
    }

    @Test
    void testUpdate() {
        ClearingCost clearingCost = Mockito.mock(ClearingCost.class);
        costMatrixService.update(clearingCost);
        verify(costMatrixRepository).update(clearingCost);
    }

}