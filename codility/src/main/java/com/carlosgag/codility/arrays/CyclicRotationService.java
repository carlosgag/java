package com.carlosgag.codility.arrays;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
@Slf4j
public class CyclicRotationService implements BiFunction<Integer[], Integer, Integer[]> {
    @Override
    public Integer[] apply(Integer[] A, Integer K) {
        if (A.length > 1) {
            K = K < A.length ? K : K / A.length;
            for (int i = 1; i <= K; i++) {
                rotate(A);
            }
        }
        return A;
    }

    private void rotate(Integer[] A) {
        int last = A[A.length - 1];
        for (int i = A.length - 1; i > 0; i--) {
            A[i] = A[i - 1];
        }
        A[0] = last;
        printArray(A);
        System.out.println("---");
    }

    private void printArray(Integer[] A) {
        for (int j : A) System.out.print(j + " ");
        System.out.println(Strings.EMPTY);
    }
}
