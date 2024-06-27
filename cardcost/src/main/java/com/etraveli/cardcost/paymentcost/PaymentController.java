package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.entities.ClearingCost;
import com.etraveli.cardcost.paymentcost.entities.CostRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment-cards-cost")
    public ClearingCost calculateCost(@RequestBody CostRequest costRequest) {
        return paymentService.calculateCost(costRequest.cardNumber());
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }

}
