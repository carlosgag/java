package com.carlosgag.codility.iterations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@Slf4j
public class BinaryGapService implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer number) {
        final var binaryNumber = Integer.toString(number, 2);
        final var binaryNumberArray = binaryNumber.toCharArray();
        log.debug("number: {}", number);
        log.debug("number as binary: {}", binaryNumber);
        int i = 0;
        int counter = 0;
        int maxCounter = 0;
        boolean counting = false;
        while (i < binaryNumberArray.length) {
            log.debug(String.valueOf(binaryNumberArray[i]));
            if (binaryNumberArray[i] == '0') {
               if(!counting) {
                   counting = true;
               }
               counter++;
                log.debug("counter: {}", counter);
            } else {
                // actual digit is 1
                if(counting) {
                    // finished counting and updating max counter if bigger
                    if(counter > maxCounter) {
                        maxCounter = counter;
                    }
                    counting = false;
                    counter = 0;
                }
            }
            i++;
        }
        return maxCounter;
    }

}
