package com.carlosgag.codility.arrays;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/arrays")
@RequiredArgsConstructor
public class ArraysController {

    private final CyclicRotationService cyclicRotationService;
    private final OddOccurrencesInArrayService oddOccurrencesInArrayService;

    @GetMapping("/cyclic-rotation/array/{array}/times/{K}")
    public ResponseEntity<Integer[]> cyclicRotation(@PathVariable String[] array, @PathVariable int K) {
        Integer [] A = new Integer[array.length];
        for(int i = 0; i < array.length; i++) {
            A[i] = Integer.parseInt(array[i]);
        }
        final var result = cyclicRotationService.apply(A, K);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/odd-occurrences-in-array/array/{array}")
    public ResponseEntity<Integer> oddOccurrencesInArray(@PathVariable String[] array) {
        Integer [] A = new Integer[array.length];
        for(int i = 0; i < array.length; i++) {
            A[i] = Integer.parseInt(array[i]);
        }
        final var result = oddOccurrencesInArrayService.apply(A);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
