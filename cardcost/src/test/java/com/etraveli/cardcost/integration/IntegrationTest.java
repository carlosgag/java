package com.etraveli.cardcost.integration;

import com.etraveli.cardcost.entities.ClearingCost;
import com.etraveli.cardcost.paymentcost.entities.CostRequest;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest implements WithAssertions {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testOk() {
        CostRequest costRequest = new CostRequest();
        costRequest.setCardNumber("123456");
        ResponseEntity<ClearingCost> result = testRestTemplate
                .postForEntity("/payment-cards-cost", costRequest, ClearingCost.class);
        assertThat(result.getBody().getCost()).isEqualTo(10.0);
    }
}
