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

}
