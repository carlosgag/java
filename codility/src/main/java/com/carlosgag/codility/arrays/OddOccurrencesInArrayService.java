package com.carlosgag.codility.arrays;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

@Service
@Slf4j
public class OddOccurrencesInArrayService implements Function<Integer[], Integer> {

    @Override
    public Integer apply(Integer[] A) {
        final Set<Integer> counter = new HashSet<>();
        Arrays.stream(A).forEach(elem -> {
            if (counter.contains(elem)) {
                counter.remove(elem);
            } else {
                counter.add(elem);
            }
        });
        return counter.iterator().next();
    }

}
