package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;
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
        return jpaRepository.findById(countryId).orElse(null);
    }

    @Override
    @Transactional
    public void post(ClearingCostData clearingCostData) {
        jpaRepository.save(clearingCostData);
    }

    @Override
    @Transactional
    public void update(ClearingCostData clearingCostData) {
        this.post(clearingCostData);
    }

    @Override
    public void delete(String countryId) {
        jpaRepository.deleteById(countryId);
    }

}
