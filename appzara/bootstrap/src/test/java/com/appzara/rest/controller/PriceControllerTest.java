package com.appzara.rest.controller;


import com.appzara.AppZaraApplication;
import com.appzara.dto.PriceDto;
import com.appzara.repository.PriceRepository;
import com.appzara.service.PriceService;
import com.appzara.usecase.PriceUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = AppZaraApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class PriceControllerTest {

    public static final String URL = "/price/startDate/{startDate}/productId/{productId}/brandId/{brandId}";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PriceUseCase useCase;

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @Test
    @DisplayName("Test get final price to the day 14 and 10:00 hrs")
    void should_return_price_list_case_1_test() throws Exception {

        //When
        var price = this.restTemplate.getForEntity("http://localhost:" + port + URL, PriceDto.class, "2020-06-14-10.00.00", "35455", "1");
        //Then
        assertTrue(price.getStatusCode().is2xxSuccessful());
        assertNotNull(price.getBody());
        assertEquals(new BigDecimal("38.95"), (price.getBody().amount()));
        assertEquals("1", price.getBody().brandId());
        assertEquals(1, price.getBody().priority());
        assertEquals(4, price.getBody().rate());
        assertEquals("35455", price.getBody().productId());
        assertEquals("EUR", price.getBody().currency());
        assertEquals(LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter), price.getBody().startDate());
        assertNotNull(price.getBody().endDate());
    }

    @Test
    @DisplayName("Test get final price to the day 14 at 16:00 hrs")
    void should_return_price_list_case_2_test() throws Exception {

        //When
        var price = this.restTemplate.getForEntity("http://localhost:" + port + URL, PriceDto.class,
                "2020-06-14-16.00.00", "35455", "1");
        //Then
        assertTrue(price.getStatusCode().is2xxSuccessful());
        assertNotNull(price.getBody());
        assertEquals(new BigDecimal("38.95"), (price.getBody().amount()));
        assertEquals("1", price.getBody().brandId());
        assertEquals(1, price.getBody().priority());
        assertEquals(4, price.getBody().rate());
        assertEquals("35455", price.getBody().productId());
        assertEquals("EUR", price.getBody().currency());
        assertEquals(LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter), price.getBody().startDate());
        assertNotNull(price.getBody().endDate());
    }

    @Test
    @DisplayName("Test get final price to the day 14 at 21:00 hrs")
    void should_return_price_list_case_3_test() throws Exception {

        //When
        var price = this.restTemplate.getForEntity("http://localhost:" + port +
                URL, PriceDto.class, "2020-06-14-21.00.00", "35455", "1");
        //Then
        assertTrue(price.getStatusCode().is2xxSuccessful());
        assertNotNull(price.getBody());
        assertEquals(new BigDecimal("38.95"), (price.getBody().amount()));
        assertEquals("1", price.getBody().brandId());
        assertEquals(1, price.getBody().priority());
        assertEquals(4, price.getBody().rate());
        assertEquals("35455", price.getBody().productId());
        assertEquals("EUR", price.getBody().currency());
        assertEquals(LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter), price.getBody().startDate());
        assertNotNull(price.getBody().endDate());
    }

    @Test
    @DisplayName("Test get final price to the day 15 at 10:00 hrs")
    void should_return_the_highest_price_test() throws Exception {

        //When
        var price = this.restTemplate.getForEntity("http://localhost:" + port +
                URL, PriceDto.class, "2020-06-15-10.00.00", "35455", "1");
        //Then
        assertTrue(price.getStatusCode().is2xxSuccessful());
        assertNotNull(price.getBody());
        assertEquals(new BigDecimal("38.95"), (price.getBody().amount()));
        assertEquals("1", price.getBody().brandId());
        assertEquals(1, price.getBody().priority());
        assertEquals(4, price.getBody().rate());
        assertEquals("EUR", price.getBody().currency());
        assertEquals("35455", price.getBody().productId());
        assertEquals(LocalDateTime.parse("2020-06-15-16.00.00", dateTimeFormatter), price.getBody().startDate());
        assertNotNull(price.getBody().endDate());
    }

    @Test
    @DisplayName("Test get final price to the day 16 at 21:00 hrs")
    void should_return_price_list_case_4_test() throws Exception {

        //When
        var price = this.restTemplate.getForEntity("http://localhost:" + port +
                URL, PriceDto.class, "2020-06-16-21.00.00", "35455", "1");

        assertTrue(price.getStatusCode().is4xxClientError());
    }
}
