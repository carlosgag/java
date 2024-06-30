package com.etraveli.cardcost.persistence.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "clearingCost")
@Data
@Builder
public class ClearingCostData {
    @Id
    private String country;
    private Double cost;
}
