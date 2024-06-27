package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costs")
public class CostMatrixController {

    private final CostMatrixService costMatrixService;

    public CostMatrixController(CostMatrixService costMatrixService) {
        this.costMatrixService = costMatrixService;
    }

    @GetMapping("/country/{countryId}")
    public ClearingCost get(@PathVariable String countryId) {
        return costMatrixService.get(countryId);
    }

    @PostMapping
    public void post(@RequestBody ClearingCost clearingCost) {
        costMatrixService.post(clearingCost);
    }

    @PutMapping
    public void put(@RequestBody ClearingCost clearingCost) {
        costMatrixService.put(clearingCost);
    }

    @DeleteMapping("/country/{countryId}")
    public void delete(@PathVariable String countryId) {
        costMatrixService.delete(countryId);
    }
}