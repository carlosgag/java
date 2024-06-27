package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CostMatrixControllerTest implements WithAssertions {
    @Mock
    CostMatrixService costMatrixService;

    private CostMatrixController costMatrixController;

    @BeforeEach
    void setup() {
        costMatrixController = new CostMatrixController(costMatrixService);
    }

    @Test
    void testGet() {
        assertThat(costMatrixController.get("test")).isNull();
    }

    @Test
    void testPost() {
        costMatrixController.post(ClearingCost.builder().build());
        verify(costMatrixService).post(any());
    }

    @Test
    void testPut() {
        costMatrixController.put(ClearingCost.builder().build());
        verify(costMatrixService).put(any());
    }
}