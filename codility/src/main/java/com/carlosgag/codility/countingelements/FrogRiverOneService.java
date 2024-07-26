package com.carlosgag.codility.countingelements;

import lombok.Builder;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class FrogRiverOneService implements Function<FrogRiverOneService.Params, Integer> {
    @Override
    public Integer apply(Params params) {
        int X = params.X;
        int[] A = params.A;
        if(A.length < X) {
            return -1;
        } else {
            boolean [] river = new boolean[X];
            int pos = -1;
            int i = -1;
            while(i < A.length - 1 && pos < X - 1) {
                i++;
                river[A[i]-1] = true;
                while(pos < X - 1 && river[pos + 1]) {
                    pos++;
                }
            }
            if(pos < X - 1) {
                return -1;
            }
            return i;
        }
    }

    @Builder
    public record Params(int X, int[] A) {}
}
