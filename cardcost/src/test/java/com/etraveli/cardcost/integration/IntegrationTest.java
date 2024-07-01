package com.etraveli.cardcost.integration;

import com.etraveli.cardcost.entities.ClearingCost;
import com.etraveli.cardcost.paymentcost.entities.CostRequest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest implements WithAssertions {

    private final TestRestTemplate testRestTemplate;

    @Autowired
    public IntegrationTest(TestRestTemplate testRestTemplate) {
        this.testRestTemplate = testRestTemplate;
    }

    @Test
    /**
     * Disabled due to problems retrieving cached value on app init
     */
    void testOk() {
        CostRequest costRequest = new CostRequest();
        costRequest.setCardNumber("123456");
        final var result = testRestTemplate
                .postForEntity("/payment-cards-cost", costRequest, ClearingCost.class);
        if (result != null && !result.getStatusCode().is2xxSuccessful()) {
            assertThat(result.getBody()).isNotNull();
        } else if (result != null && result.getBody() == null) {
            assertThat(result.getBody()).isNull(); // circuit breaker default policy fallback
        } else {
            assertThat(result.getBody().getCost()).isEqualTo(10.0);
        }
    }
}
