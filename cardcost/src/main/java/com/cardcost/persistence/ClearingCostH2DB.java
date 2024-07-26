package com.cardcost.persistence;

import com.cardcost.persistence.data.ClearingCostData;
import com.cardcost.persistence.exceptions.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ClearingCostH2DB implements DB<ClearingCostData, String> {
    private final ClearingCostJPARepository clearingCostJpaRepository;

    public ClearingCostH2DB(ClearingCostJPARepository clearingCostJpaRepository) {
        this.clearingCostJpaRepository = clearingCostJpaRepository;
    }

    @Override
    public ClearingCostData get(String countryId) {
        try {
            return clearingCostJpaRepository.findById(countryId).orElse(null);
        } catch (Exception e) {
            throw new PersistenceException("Error getting ClearingCost", e);
        }
    }

    @Override
    @Transactional
    public void post(ClearingCostData clearingCostData) {
        try {
            clearingCostJpaRepository.save(clearingCostData);
        } catch (Exception e) {
            throw new PersistenceException("Error adding ClearingCost", e);
        }
    }

    @Override
    @Transactional
    public void update(ClearingCostData clearingCostData) {
        try {
            this.post(clearingCostData);
        } catch (Exception e) {
            throw new PersistenceException("Error updating ClearingCost", e);
        }
    }

    @Override
    public void delete(String countryId) {
        try {
            clearingCostJpaRepository.deleteById(countryId);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting ClearingCost", e);
        }
    }

}
