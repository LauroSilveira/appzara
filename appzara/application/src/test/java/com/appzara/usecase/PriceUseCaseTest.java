package com.appzara.usecase;

import com.appzara.dto.PriceDto;
import com.appzara.entity.Price;
import com.appzara.service.PriceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        when(this.priceService.getPrice(Mockito.any(), Mockito.anyString(), Mockito.anyString()))
                .thenReturn(List.of(
                        new PriceDto(1, "35455", 1, 1, LocalDateTime.now(), LocalDateTime.now(),
                                new BigDecimal(23)),
                        new PriceDto(1, "35455", 0, 1, LocalDateTime.now(), LocalDateTime.now(),
                                new BigDecimal(23)),
                        new PriceDto(1, "35455", 0, 1, LocalDateTime.now(), LocalDateTime.now(),
                                new BigDecimal(23))
                ));
        //When
        final var prices = this.priceUseCase.getPrice("2023-09-19-10.23.00", "35455", "1");

        //Then
        assertNotNull(prices);
        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
    }

    @Test
    void should_throws_dateTimeException_test() {

        assertThrows(DateTimeException.class, () ->
                this.priceUseCase.getPrice(LocalDateTime.now().toString(), "35455", "1"));
    }
}