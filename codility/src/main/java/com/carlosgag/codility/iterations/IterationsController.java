package com.carlosgag.codility.iterations;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/iterations")
@RequiredArgsConstructor
public class IterationsController {
    private final BinaryGapService binaryGapService;

    @GetMapping("/binarygap/{number}")
    public ResponseEntity<Integer> binaryGap(@PathVariable Integer number) {
        final var result = binaryGapService.apply(number);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
