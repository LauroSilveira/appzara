package com.appzara.service;

import com.appzara.entity.Price;
import com.appzara.exception.ResourceNotFoundException;
import com.appzara.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PriceServiceImplTest {

    @SpyBean
    private PriceServiceImpl priceService;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    void return_price_correctly_test() {
        //Given
        when(this.priceRepository.findAllByStartDateIsGreaterThanAndProductIdAndBrandId(any(), anyString(), anyString()))
                .thenReturn(List.of(Price.builder()
                                .id(1L)
                                .amount(new BigDecimal(500))
                                .rate(2)
                                .productId("35455")
                                .priority(0)
                                .currency("EUR")
                                .brandId("1")
                                .startDate(LocalDateTime.of(2023, 9, 19, 9, 48))
                                .endDate(LocalDateTime.of(2023, 9, 19, 18, 0))
                                .build(),
                        Price.builder()
                                .id(1L)
                                .amount(new BigDecimal(500))
                                .rate(2)
                                .productId("35455")
                                .priority(1)
                                .currency("EUR")
                                .brandId("1")
                                .startDate(LocalDateTime.of(2023, 9, 19, 9, 48))
                                .endDate(LocalDateTime.of(2023, 9, 19, 18, 0))
                                .build())
                );
        //When
        final var prices = this.priceService.getPrice(LocalDateTime.of(2020, 6, 15, 16, 0, 0), "35455", "1");

        //Then
        assertNotNull(prices);
        assertFalse(prices.isEmpty());
        assertEquals(2, prices.size());
    }

    @Test
    void should_throw_resourceNotFoundexception_test() {
        //Given
        when(this.priceRepository.findAllByStartDateIsGreaterThanAndProductIdAndBrandId(any(), anyString(), anyString()))
                .thenReturn(List.of());

        //Then
        assertThrows(ResourceNotFoundException.class, () ->
                this.priceService.getPrice(LocalDateTime.of(2020, 6, 15, 16,0,0),
                        "35455", "1"));
    }
}