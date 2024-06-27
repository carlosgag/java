package com.etraveli.cardcost.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClearingCost {
    private String country;
    private Double cost;
}
