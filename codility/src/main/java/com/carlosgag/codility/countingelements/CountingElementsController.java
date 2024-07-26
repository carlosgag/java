package com.carlosgag.codility.countingelements;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/counting-elements")
@RequiredArgsConstructor
public class CountingElementsController {

    private final FrogRiverOneService frogRiverOneService;

    @GetMapping("/frog-river-one/x/{x}/array/{array}")
    public ResponseEntity<Integer> permMissingElem(@PathVariable Integer x, @PathVariable String[] array) {
        int[] A = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            A[i] = Integer.parseInt(array[i]);
        }
        final var result = frogRiverOneService.apply(FrogRiverOneService.Params.builder()
                .X(x)
                .A(A)
                .build());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
