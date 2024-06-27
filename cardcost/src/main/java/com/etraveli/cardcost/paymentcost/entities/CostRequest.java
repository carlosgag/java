package com.etraveli.cardcost.paymentcost.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CostRequest(
        @JsonProperty("card_number")
        @NonNull
                @Size(min = 2, max = 9)
        String cardNumber) {
}
