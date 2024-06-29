package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.entities.ClearingCost;
import com.etraveli.cardcost.paymentcost.entities.CostRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(value = "/payment-cards-cost",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ClearingCost calculateCost(@Valid @NotNull(message = "Body cannot be null") @RequestBody CostRequest costRequest) {
        return paymentService.calculateCost(costRequest.getCardNumber());
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
