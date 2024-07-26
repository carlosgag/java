package com.cardcost.integration;

import com.cardcost.entities.ClearingCost;
import com.cardcost.paymentcost.entities.CostRequest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

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
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
}
