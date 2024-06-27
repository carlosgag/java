package com.etraveli.cardcost.paymentcost.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CostRequest(@JsonProperty("card_number") String cardNumber) {
}
