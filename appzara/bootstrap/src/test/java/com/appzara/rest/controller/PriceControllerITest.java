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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = AppZaraApplication.class)
@ActiveProfiles("test")
class PriceControllerITest {

    private static final ObjectMapper mapper = new ObjectMapper();
    public static final String URL = "/price/startDate/{startDate}/productId/{productId}/brandId/{brandId}";

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
    @DisplayName("Should return pice list for day 14 at 10.00.00 hrs")
    void should_return_price_list_case_1_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-14-10.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
    }

    @Test
    @DisplayName("Should return pice list for day 14 at 16.00.00 hrs")
    void should_return_price_list_case_2_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-14-16.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
    }

    @Test
    @DisplayName("Should return pice list for day 14 at 21.00.00 hrs")
    void should_return_price_list_case_3_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-14-21.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
    }

    @Test
    @DisplayName("Should return pice list for day 15 at 10.00.00 hrs")
    void should_return_price_list_case_4_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-15-10.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
    }

    @Test
    @DisplayName("Should return pice list for day 16 at 21.00.00 hrs")
    void should_return_price_list_case_5_test() throws Exception {

        //When
        MvcResult response = this.mockMvc.perform(get(
                        URL, "2020-06-16-21.00.00", "35455", "1")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        //Then
        assertNotNull(response.getResponse().getContentAsString());

        List<PriceDto> prices = mapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});
        assertFalse(prices.isEmpty());
    }
}
