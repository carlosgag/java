package com.etraveli.cardcost.persistence.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clearingCost")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClearingCostData {
    @Id
    private String country;
    private Double cost;
}
