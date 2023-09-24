package com.appzara.rest.controller;


import com.appzara.AppZaraApplication;
import com.appzara.dto.PriceDto;
import com.appzara.repository.PriceRepository;
import com.appzara.service.PriceService;
import com.appzara.usecase.PriceUseCase;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppZaraApplication.class)
@ActiveProfiles("test")
class PriceControllerITest {

    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String URL = "/price/startDate/{startDate}/endDate/{endDate}/productId/{productId}/brandId/{brandId}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceUseCase useCase;

    @Autowired
    private PriceService priceService;

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setup() {
        mapper.registerModule(new JavaTimeModule());
    }

    @Test
    void should_return_price_list_case_1_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-14-10.00.00", "2020-12-31-23.59.59", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
        assertEquals(new BigDecimal("38.95"), (prices.get(0).amount()));
    }

    @Test
    void should_return_price_list_case_2_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-15-00.00.00", "2020-06-15-11.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
        assertEquals(new BigDecimal("38.95"), (prices.get(0).amount()));
    }

    @Test
    void should_return_price_list_case_3_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-15-16.00.00", "2020-12-31-23.59.59", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
        assertEquals(new BigDecimal("30.50"), (prices.get(0).amount()));
    }

    @Test
    void should_return_the_highest_price_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-14-15.00.00", "2020-06-14-18.30.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertFalse(prices.isEmpty());
        assertEquals(1, prices.size());
        assertEquals(new BigDecimal("38.95"), (prices.get(0).amount()));
    }
}
