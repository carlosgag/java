package com.carlosgag.codility.timecomplexity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class TapeEquilibriumService implements Function<Integer[], Integer> {
    @Override
    public Integer apply(Integer[] A) {
        int pre = 0;
        int result = Integer.MAX_VALUE;

        int post = 0;
        for(int i : A) {
            post += i;
        }

        for(int p = 0; p < A.length - 1; p++) {
            pre = pre + A[p];
            post = post - A[p];
            int actual = Math.abs(pre - post);

            if(actual < result) {
                result = actual;
            }
        }
        return result;
    }

}
