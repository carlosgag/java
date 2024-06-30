package com.etraveli.cardcost.entities;

import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Data
@Validated
@Builder
public class ClearingCost implements Serializable {
    private String country;
    private Double cost;
}
