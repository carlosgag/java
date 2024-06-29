package com.etraveli.cardcost.entities;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClearingCost implements Serializable {
    private String country;
    private Double cost;
}
