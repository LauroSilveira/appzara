package com.appzara.rest.controller.advice;


import com.appzara.exception.ResourceNotFoundException;
import com.appzara.service.PriceService;
import com.appzara.usecase.PriceUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.HttpServerErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GlobalRestControllerAdviceTest {

    public static final String URL = "/price/startDate/{startDate}/endDate/{endDate}/productId/{productId}/brandId/{brandId}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceUseCase useCase;

    @MockBean
    private PriceService priceService;

    @Test
    void return_not_found_when_get_price_is_null_test() throws Exception {
        //Given

        Mockito.when(this.priceService.getPrice(any(), any(), anyString(), anyString()))
                .thenThrow(new ResourceNotFoundException("Resource not found"));

        //When
        final var response = this.mockMvc.perform(get(URL,
                        "2020-06-14-00.00.00", "2020-06-14-00.00.00", 35400, 2))
                .andDo(print())
                .andReturn();

        //Then
        assertEquals(NOT_FOUND, valueOf(response.getResponse().getStatus()));
        assertNotNull(response.getResolvedException());
        assertEquals("Resource not found", response.getResolvedException().getMessage());
    }

    @Test
    void return_bad_request_when_date_is_invalid_test() throws Exception {
        //When
        final var response = this.mockMvc.perform(get(URL, "2020-06-14", "2020-06-16", 35455, 1))
                .andDo(print())
                .andReturn();

        //Then
        assertEquals(BAD_REQUEST, valueOf(response.getResponse().getStatus()));
        assertNotNull(response.getResolvedException());
        assertEquals("Invalid DateTime formmat", response.getResolvedException().getMessage());
    }

    @Test
    void return_internal_server_error_test() throws Exception {

        //Given
        Mockito.when(this.priceService.getPrice(any(), any(), anyString(), anyString()))
                .thenThrow(HttpServerErrorException.InternalServerError.class);

        //When
        final var response = this.mockMvc.perform(get(URL,
                        "2020-06-14-00.00.00", "2020-12-31-23.59.59", 35455, 2))
                .andDo(print())
                .andReturn();
        //Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, valueOf(response.getResponse().getStatus()));
    }
}
