package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;
import com.etraveli.cardcost.persistence.exceptions.PersistenceException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class H2DB implements DB {
    private final JPARepository jpaRepository;

    public H2DB(JPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public ClearingCostData get(String countryId) {
        try {
            return jpaRepository.findById(countryId).orElse(null);
        } catch (Exception e) {
            throw new PersistenceException("Error getting ClearingCost", e);
        }
    }

    @Override
    @Transactional
    public void post(ClearingCostData clearingCostData) {
        try {
            jpaRepository.save(clearingCostData);
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
            jpaRepository.deleteById(countryId);
        } catch (Exception e) {
            throw new PersistenceException("Error deleting ClearingCost", e);
        }
    }

}
