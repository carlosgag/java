package com.etraveli.cardcost.clearingcostmatrix;

import com.etraveli.cardcost.entities.ClearingCost;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costs")
public class CostMatrixController {

    private final CostMatrixService costMatrixService;

    public CostMatrixController(CostMatrixService costMatrixService) {
        this.costMatrixService = costMatrixService;
    }

    @GetMapping("/country/{countryId}")
    public ClearingCost get(@PathVariable @NotEmpty String countryId) {
        return costMatrixService.get(countryId);
    }

    @PostMapping
    public void post(@RequestBody @NotEmpty ClearingCost clearingCost) {
        costMatrixService.post(clearingCost);
    }

    @PutMapping
    public void put(@RequestBody ClearingCost clearingCost) {
        costMatrixService.put(clearingCost);
    }

    @DeleteMapping("/country/{countryId}")
    public void delete(@PathVariable @NotEmpty String countryId) {
        costMatrixService.delete(countryId);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}