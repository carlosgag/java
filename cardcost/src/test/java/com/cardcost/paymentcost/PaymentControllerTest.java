package com.cardcost.paymentcost;

import com.cardcost.entities.ClearingCost;
import com.cardcost.paymentcost.entities.CostRequest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest implements WithAssertions {
    public static final double EXPECTED = 10.0;
    @Mock
    private PaymentService paymentService;

    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        paymentController = new PaymentController(paymentService);
    }

    @Test
    void testOk() {
        when(paymentService.calculateCost(any())).thenReturn(ClearingCost.builder().cost(EXPECTED).build());
        CostRequest costRequest = new CostRequest();
        costRequest.setCardNumber("123456");
        final var result = paymentController.calculateCost(costRequest);
        assertThat(result.getBody().getCost()).isEqualTo(EXPECTED);
    }


    @Test
    void testHealth() {
        final var result = paymentController.health();
        assertThat(result).isEqualTo("OK");
    }

}