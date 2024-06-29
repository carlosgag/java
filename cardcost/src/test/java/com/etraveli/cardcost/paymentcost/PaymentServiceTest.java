package com.etraveli.cardcost.paymentcost;

import com.etraveli.cardcost.binlist.BinListRepository;
import com.etraveli.cardcost.binlist.entities.Country;
import com.etraveli.cardcost.binlist.entities.Response;
import com.etraveli.cardcost.clearingcostmatrix.CostMatrixService;
import com.etraveli.cardcost.entities.ClearingCost;
import org.apache.logging.log4j.util.Strings;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PaymentServiceTest implements WithAssertions {

    public static final String CARD_NUMBER = "123456";
    public static final String COUNTRY_CODE = "DK";
    public static final double COST = 10.0;
    @Mock
    private BinListRepository binListRepository;

    @Mock
    private CostMatrixService costMatrixService;

    @Mock
    private GetIIN getIIN;

    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService = new PaymentService(binListRepository, costMatrixService, getIIN);
    }

    @ParameterizedTest
    @ValueSource(strings = {CARD_NUMBER, Strings.EMPTY})
    void testOk(String cardNumber) {
        when(getIIN.apply(cardNumber))
                .thenReturn(cardNumber);
        Country country = Mockito.mock(Country.class);
        when(country.alpha2()).thenReturn(COUNTRY_CODE);
        Response response = Mockito.mock(Response.class);
        when(response.country())
                .thenReturn(country);
        when(binListRepository.get(cardNumber))
                .thenReturn(response);

        ClearingCost clearingCost = Mockito.mock(ClearingCost.class);
        when(clearingCost.getCost())
                .thenReturn(COST);
        when(costMatrixService.get(COUNTRY_CODE))
                .thenReturn(clearingCost);

        final var result = paymentService.calculateCost(cardNumber);
        assertThat(result.getCost()).isEqualTo(COST);
    }

    @Test
    void testNullCountry() {
        when(getIIN.apply(CARD_NUMBER))
                .thenReturn(CARD_NUMBER);
        Response response = Mockito.mock(Response.class);
        when(binListRepository.get(CARD_NUMBER))
                .thenReturn(response);

        assertThat(paymentService.calculateCost(CARD_NUMBER)).isNull();
    }

}