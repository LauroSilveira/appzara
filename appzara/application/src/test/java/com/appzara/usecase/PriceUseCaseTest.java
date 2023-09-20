package com.appzara.usecase;

import com.appzara.dto.PriceDto;
import com.appzara.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PriceUseCaseTest {

    @SpyBean
    private PriceUseCase priceUseCase;

    @MockBean
    private PriceService priceService;

    @Test
    void should_return_prices_list_correctly_test() {
        //Given
        when(this.priceService.getPrice(any(), any(), anyString(), anyString()))
                .thenReturn(List.of(
                        new PriceDto(1, "35455", 1, 1, LocalDateTime.of(2020, 6, 14, 0, 0, 0),
                                LocalDateTime.of(2020, 12, 31, 23, 59, 59),
                                new BigDecimal(23)),
                        new PriceDto(1, "35455", 0, 1, LocalDateTime.of(2020, 6, 14, 15, 0, 0),
                                LocalDateTime.of(2020, 6, 14, 18, 30, 0),
                                new BigDecimal(23)),
                        new PriceDto(1, "35455", 0, 1, LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                                LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                                new BigDecimal(23))
                ));
        //When
        final var prices = this.priceUseCase.getPrice("2023-09-19-10.23.00", "2023-09-20-10.23.00", "35455", "1");

        //Then
        assertNotNull(prices);
        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
    }

    @Test
    void should_throws_dateTimeException_test() {

        assertThrows(DateTimeException.class, () ->
                this.priceUseCase.getPrice(LocalDateTime.now().toString(), LocalDateTime.now().toString(), "35455", "1"));
    }

    @Test
    void should_return_list_with_one_element() {
        //Given
        when(this.priceService.getPrice(any(), any(), anyString(), anyString()))
                .thenReturn(List.of(new PriceDto(1, "35455", 0, 1,
                        LocalDateTime.of(2020, 6, 15, 0, 0, 0),
                        LocalDateTime.of(2020, 6, 15, 11, 0, 0),
                        new BigDecimal(23))));

        //When
        final var prices = this.priceUseCase.getPrice("2020-06-15-00.00.00", "2020-06-15-11.00.00", "35455", "1");

        //Then
        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
    }
}