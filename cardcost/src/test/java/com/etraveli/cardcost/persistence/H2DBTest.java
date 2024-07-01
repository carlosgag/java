package com.etraveli.cardcost.persistence;

import com.etraveli.cardcost.persistence.data.ClearingCostData;
import com.etraveli.cardcost.persistence.exceptions.PersistenceException;
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
class H2DBTest implements WithAssertions {

    public static final String COUNTRY_ID = "countryId";
    public static final double COST = 10.11;
    public static final double UPDATED_COST = 12.0;

    @Mock
    private JPARepository jpaRepository;

    private H2DB h2DB;

    @BeforeEach
    void setup() {
        h2DB = new H2DB(jpaRepository);
    }

    @Test
    void testAllOperations() {
        ClearingCostData clearingCostData = new ClearingCostData();
        clearingCostData.setCountry(COUNTRY_ID);
        clearingCostData.setCost(COST);
        when(jpaRepository.save(any())).thenReturn(clearingCostData);
        h2DB.post(clearingCostData);
        when(jpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.of(clearingCostData));
        final var result = h2DB.get(COUNTRY_ID);
        assertThat(result.getCost()).isEqualTo(COST);

        result.setCost(UPDATED_COST);
        when(jpaRepository.save(any())).thenReturn(result);
        h2DB.update(result);
        when(jpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.of(result));
        final var updatedResult = h2DB.get(COUNTRY_ID);
        assertThat(updatedResult.getCost()).isEqualTo(UPDATED_COST);

        doNothing().when(jpaRepository).deleteById(COUNTRY_ID);
        h2DB.delete(COUNTRY_ID);
        when(jpaRepository.findById(COUNTRY_ID)).thenReturn(Optional.empty());
        final var deletedResult = h2DB.get(COUNTRY_ID);
        assertThat(deletedResult).isNull();
    }

    @Test
    void testGetException() {
        when(jpaRepository.findById(any())).thenThrow(new IllegalArgumentException());
        assertThrows(PersistenceException.class, () -> h2DB.get(COUNTRY_ID));

    }

    @Test
    void testPostException() {
        when(jpaRepository.save(any())).thenThrow(new IllegalArgumentException());
        assertThrows(PersistenceException.class, () -> h2DB.post(new ClearingCostData()));
    }

    @Test
    void testUpdateException() {
        doThrow(IllegalArgumentException.class).when(jpaRepository).save(any());
        assertThrows(PersistenceException.class, () -> h2DB.update(new ClearingCostData()));
    }

    @Test
    void testDeleteException() {
        doThrow(IllegalArgumentException.class).when(jpaRepository).deleteById(COUNTRY_ID);
        assertThrows(PersistenceException.class, () -> h2DB.delete(COUNTRY_ID));
    }
}