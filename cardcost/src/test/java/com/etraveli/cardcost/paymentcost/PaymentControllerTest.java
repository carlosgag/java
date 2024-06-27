package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.paymentcost.entities.CostRequest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest implements WithAssertions {
    @Mock
    private PaymentService paymentService;

    private PaymentController paymentController;

    @BeforeEach
    public void setup() {
        paymentController = new PaymentController(paymentService);
    }

    @Test
    void testOk() {
//        when(paymentService.calculateCost(any()))
//                .thenReturn(ClearingCost.builder().build());
        final var result = paymentController.calculateCost(CostRequest.builder().build());
        assertThat(result).isNull();
    }

    @Test
    void testHealth() {
        final var result = paymentController.health();
        assertThat(result).isEqualTo("OK");
    }

}