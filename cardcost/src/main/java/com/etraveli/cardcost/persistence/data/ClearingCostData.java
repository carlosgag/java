package com.etraveli.cardcost.persistence.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
