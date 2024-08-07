package com.cardcost.paymentcost;

import com.cardcost.binlist.BinListRepository;
import com.cardcost.binlist.entities.Response;
import com.cardcost.binlist.exceptions.ExternalAPIException;
import com.cardcost.clearingcostmatrix.CostMatrixService;
import com.cardcost.entities.ClearingCost;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final BinListRepository binListRepository;
    private final CostMatrixService costMatrixService;
    private final GetIIN getIIN;
    private final GetCachedCost getCachedCost;

    public PaymentService(BinListRepository binListRepository,
                          CostMatrixService costMatrixService,
                          GetIIN getIIN, GetCachedCost getCachedCost) {
        this.binListRepository = binListRepository;
        this.costMatrixService = costMatrixService;
        this.getIIN = getIIN;
        this.getCachedCost = getCachedCost;
    }


    @Cacheable(value = "calculateCost")
    public ClearingCost calculateCost(String cardNumber) {
        Response response;
        try {
            response = binListRepository.get(getIIN.apply(cardNumber));
            if (response.country() != null && response.country().alpha2() != null) {
                ClearingCost clearingCost = costMatrixService.get(response.country().alpha2());
                if (clearingCost != null && clearingCost.getCountry() == null) {
                    clearingCost.setCountry("Others");
                    clearingCost.setCost(10.0);
                }
                return clearingCost;
            }
            return null;
        } catch (ExternalAPIException e) {
            ClearingCost cached = getCachedCost.apply(cardNumber);
            if (cached != null) {
                return cached;
            } else {
                throw e;
            }
        }
    }
}
