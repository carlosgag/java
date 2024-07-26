package com.cardcost.clearingcostmatrix;

import com.cardcost.entities.ClearingCost;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/costs")
@Validated
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
    public void post(@Valid @RequestBody @NotNull ClearingCost clearingCost) {
        costMatrixService.post(clearingCost);
    }

    @PutMapping
    public void update(@RequestBody ClearingCost clearingCost) {
        costMatrixService.update(clearingCost);
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