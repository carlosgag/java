package com.cardcost.persistence;

import com.cardcost.persistence.data.ClearingCostData;
import com.cardcost.persistence.exceptions.PersistenceException;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClearingCostH2DBTest implements WithAssertions {

    public static final String COUNTRY_ID = "countryId";
    public static final double COST = 10.11;
    public static final double UPDATED_COST = 12.0;

    @Mock
    private ClearingCostJPARepository clearingCostJpaRepository;

    private ClearingCostH2DB clearingCostH2DB;

    @BeforeEach
    void setup() {
        clearingCostH2DB = new ClearingCostH2DB(clearingCostJpaRepository);
    }

    @Test
    void testAllOperations() {
        ClearingCostData clearingCostData = new ClearingCostData();
        clearingCostData.setCountry(COUNTRY_ID);
        clearingCostData.setCost(COST);
        when(clearingCostJpaRepository.save(any())).thenReturn(clearingCostData);
        clearingCostH2DB.post(clearingCostData);
        when(clearingCostJpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.of(clearingCostData));
        final var result = clearingCostH2DB.get(COUNTRY_ID);
        assertThat(result.getCost()).isEqualTo(COST);

        result.setCost(UPDATED_COST);
        when(clearingCostJpaRepository.save(any())).thenReturn(result);
        clearingCostH2DB.update(result);
        when(clearingCostJpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.of(result));
        final var updatedResult = clearingCostH2DB.get(COUNTRY_ID);
        assertThat(updatedResult.getCost()).isEqualTo(UPDATED_COST);

        doNothing().when(clearingCostJpaRepository).deleteById(COUNTRY_ID);
        clearingCostH2DB.delete(COUNTRY_ID);
        when(clearingCostJpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.empty());
        final var deletedResult = clearingCostH2DB.get(COUNTRY_ID);
        assertThat(deletedResult).isNull();
    }

    @Test
    void testGetException() {
        when(clearingCostJpaRepository.findById(any())).thenThrow(new IllegalArgumentException());
        assertThrows(PersistenceException.class, () -> clearingCostH2DB.get(COUNTRY_ID));

    }

    @Test
    void testPostException() {
        when(clearingCostJpaRepository.save(any())).thenThrow(new IllegalArgumentException());
        assertThrows(PersistenceException.class, () -> clearingCostH2DB.post(new ClearingCostData()));
    }

    @Test
    void testUpdateException() {
        doThrow(IllegalArgumentException.class).when(clearingCostJpaRepository).save(any());
        assertThrows(PersistenceException.class, () -> clearingCostH2DB.update(new ClearingCostData()));
    }

    @Test
    void testDeleteException() {
        doThrow(IllegalArgumentException.class).when(clearingCostJpaRepository).deleteById(COUNTRY_ID);
        assertThrows(PersistenceException.class, () -> clearingCostH2DB.delete(COUNTRY_ID));
    }
}