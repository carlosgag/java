package com.carlosgag.codility.timecomplexity;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class FrogJmpService implements Function<FrogJmpService.Params, Integer> {

    @Override
    public Integer apply(Params params) {
        double result = Math.ceil((params.y - params.x) / (double) params.d);
        return (int) result;
    }

    @Builder
    record Params(Integer x, Integer y, Integer d) {
    }
}
