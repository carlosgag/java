package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.binlist.BinListRepository;
import com.etraveli.cardcost.binlist.entities.Response;
import com.etraveli.cardcost.clearingcostmatrix.CostMatrixService;
import com.etraveli.cardcost.entities.ClearingCost;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final BinListRepository binListRepository;
    private final CostMatrixService costMatrixService;
    private final GetIIN getIIN;

    public PaymentService(BinListRepository binListRepository, CostMatrixService costMatrixService, GetIIN getIIN) {
        this.binListRepository = binListRepository;
        this.costMatrixService = costMatrixService;
        this.getIIN = getIIN;
    }

    @Cacheable(value = "calculateCost")
    public ClearingCost calculateCost(String cardNumber) {
        Response response = binListRepository.get(getIIN.apply(cardNumber));
        if (response.country() != null) {
            ClearingCost clearingCost = costMatrixService.get(response.country().alpha2());
            if (clearingCost == null) {
                clearingCost = ClearingCost.builder().country("Others").cost(10.0).build();
            }
            return clearingCost;
        }
        return null;
    }
}
