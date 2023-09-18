package com.appzara.rest.controller;


import com.appzara.dto.PriceDto;
import com.appzara.exception.ResourceNotFoundException;
import com.appzara.usecase.PriceUseCase;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/price")
@Slf4j
public class PriceController {

    private final PriceUseCase priceUseCase;

    public PriceController(PriceUseCase priceUseCase) {
        this.priceUseCase = priceUseCase;
    }

    @GetMapping(value = "/startDate/{startDate}/productId/{productId}/brandId/{brandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PriceDto>> getPrice(@PathVariable("startDate") @NotNull @DateTimeFormat(pattern = "yyyy-MM-dd-HH.mm.ss") final String startDate,
                                                   @PathVariable("productId") @NotNull final String productId,
                                                   @PathVariable("brandId") @NotNull final String brandId) {
        log.info("PriceController - method getPrice received request with parameters startDate: {} - productId: {} - brandId: {}", startDate,
                productId, brandId);
        List<PriceDto> priceDto = this.priceUseCase.getPrice(startDate, productId, brandId);

        return ResponseEntity.status(HttpStatus.OK).body(priceDto);

    }
}
