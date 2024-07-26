package com.cardcost.paymentcost;

import com.cardcost.binlist.BinListRepository;
import com.cardcost.binlist.entities.Country;
import com.cardcost.binlist.entities.Response;
import com.cardcost.binlist.exceptions.ExternalAPIException;
import com.cardcost.clearingcostmatrix.CostMatrixService;
import com.cardcost.entities.ClearingCost;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
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

    @Mock
    private GetCachedCost getCachedCost;

    private PaymentService paymentService;

    @BeforeEach
    void setup() {
        paymentService = new PaymentService(binListRepository, costMatrixService, getIIN, getCachedCost);
    }

    @Test
    void testExceptionCatchingHasCache() {
        doThrow(ExternalAPIException.class).when(binListRepository).get(any());
        ClearingCost clearingCost = ClearingCost.builder()
                .cost(COST)
                .build();
        when(getCachedCost.apply(CARD_NUMBER))
                .thenReturn(clearingCost);
        final var result = paymentService.calculateCost(CARD_NUMBER);
        assertThat(result.getCost()).isEqualTo(COST);
    }

    @Test
    void testExceptionCatchingHasNotCache() {
        doThrow(ExternalAPIException.class).when(binListRepository).get(any());
        when(getCachedCost.apply(CARD_NUMBER))
                .thenReturn(null);
        assertThatExceptionOfType(ExternalAPIException.class)
                .isThrownBy(() -> paymentService.calculateCost(CARD_NUMBER));
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