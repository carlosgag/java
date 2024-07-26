package com.cardcost.persistence;

import com.cardcost.persistence.data.ClearingCostData;
import org.springframework.data.repository.CrudRepository;

public interface ClearingCostJPARepository extends CrudRepository<ClearingCostData, String> {
}
