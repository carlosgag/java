package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.binlist.BinListRepository;
import com.etraveli.cardcost.binlist.entities.Response;
import com.etraveli.cardcost.clearingcostmatrix.CostMatrixService;
import com.etraveli.cardcost.entities.ClearingCost;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final BinListRepository binListRepository;
    private final CostMatrixService costMatrixService;

    public PaymentService(BinListRepository binListRepository, CostMatrixService costMatrixService) {
        this.binListRepository = binListRepository;
        this.costMatrixService = costMatrixService;
    }

    public ClearingCost calculateCost(String cardNumber) {
        String bin = cardNumber.substring(0, 5);
        Response response = binListRepository.get(bin);
        ClearingCost clearingCost = costMatrixService.get(response.country().alpha2());
        if (clearingCost == null) {
            clearingCost = ClearingCost.builder().country("Others").cost(10.0).build();
        }
        return clearingCost;
    }
}
