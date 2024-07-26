package com.carlosgag.codility.timecomplexity;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-complexity")
@RequiredArgsConstructor
public class TimeComplexityController {
    private final FrogJmpService frogJmpService;
    private final PermMissingElemService permMissingElemService;
    private final TapeEquilibriumService tapeEquilibriumService;

    @GetMapping("/frog-jump/x/{x}/y/{y}/d/{d}")
    public ResponseEntity<Integer> frogJump(@PathVariable Integer x,
                                            @PathVariable Integer y,
                                            @PathVariable Integer d) {
        final var result = frogJmpService.apply(FrogJmpService.Params.builder()
                .x(x)
                .y(y)
                .d(d)
                .build());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/perm-missing-elem/array/{array}")
    public ResponseEntity<Integer> permMissingElem(@PathVariable String [] array) {
        Integer [] A = new Integer[array.length];
        for(int i = 0; i < array.length; i++) {
            A[i] = Integer.parseInt(array[i]);
        }
        final var result = permMissingElemService.apply(A);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/tape-equilibrium/array/{array}")
    public ResponseEntity<Integer> tapeEquilibrium(@PathVariable String [] array) {
        Integer [] A = new Integer[array.length];
        for(int i = 0; i < array.length; i++) {
            A[i] = Integer.parseInt(array[i]);
        }
        final var result = tapeEquilibriumService.apply(A);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
