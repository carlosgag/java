package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
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

    public void put(ClearingCost clearingCost) {
        costMatrixRepository.put(clearingCost);
    }

    public void delete(String countryId) {
        costMatrixRepository.delete(countryId);
    }
}
