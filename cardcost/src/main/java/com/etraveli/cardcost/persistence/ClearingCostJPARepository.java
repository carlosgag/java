package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;
import org.springframework.data.repository.CrudRepository;

public interface ClearingCostJPARepository extends CrudRepository<ClearingCostData, String> {
}
