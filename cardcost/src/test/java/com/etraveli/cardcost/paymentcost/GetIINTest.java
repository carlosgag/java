package com.etraveli.cardcost.paymentcost;

import org.apache.logging.log4j.util.Strings;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class GetIINTest implements WithAssertions {

    private GetIIN getIIN;

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(null, Strings.EMPTY),
                Arguments.of(Strings.EMPTY, Strings.EMPTY),
                Arguments.of("1234", Strings.EMPTY),
                Arguments.of("123456789", "123456")
        );
    }

    @BeforeEach
    void setup() {
        getIIN = new GetIIN();
    }

    @ParameterizedTest
    @MethodSource("params")
    void testCardNumberFlow(String cardNumber, String expected) {
        final var result = getIIN.apply(cardNumber);
        assertThat(result).isEqualTo(expected);
    }
}