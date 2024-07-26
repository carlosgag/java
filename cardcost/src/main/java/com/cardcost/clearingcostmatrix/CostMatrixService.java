package com.cardcost.clearingcostmatrix;

import com.cardcost.entities.ClearingCost;
import org.springframework.stereotype.Service;

@Service
public class CostMatrixService {
    private final CostMatrixRepository costMatrixRepository;

    public CostMatrixService(CostMatrixRepository costMatrixRepository) {
        this.costMatrixRepository = costMatrixRepository;
    }

    public ClearingCost get(String country) {
        return costMatrixRepository.get(country);
    }

    public void post(ClearingCost clearingCost) {
        costMatrixRepository.post(clearingCost);
    }

    public void update(ClearingCost clearingCost) {
        costMatrixRepository.update(clearingCost);
    }

    public void delete(String countryId) {
        costMatrixRepository.delete(countryId);
    }
}
