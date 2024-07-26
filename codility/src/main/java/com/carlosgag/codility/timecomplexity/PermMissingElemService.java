package com.carlosgag.codility.timecomplexity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class PermMissingElemService implements Function<Integer[], Integer> {
    @Override
    public Integer apply(Integer[] A) {
        boolean[] allNumbers = new boolean[A.length + 1];
        for (int i = 0; i < A.length; i++) {
            allNumbers[A[i]-1] = true;
        }
        for (int i = 0; i < allNumbers.length; i++) {
            if (!allNumbers[i]) {
                return i + 1;
            }
        }
        return 0;
    }
}
