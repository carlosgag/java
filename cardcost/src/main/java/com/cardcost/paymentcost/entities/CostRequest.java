package com.cardcost.paymentcost.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@NoArgsConstructor
public class CostRequest {
    @JsonProperty("card_number")
    @NotEmpty(message = "Card number cannot be empty")
    @Pattern(regexp = "^\\d{6}$", message = "Card number must contain 6 digits")
    private String cardNumber;

}
