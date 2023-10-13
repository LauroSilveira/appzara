package com.appzara.rest.controller;


import com.appzara.dto.PriceDto;
import com.appzara.mapper.PriceDtoMapper;
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

@RestController
@RequestMapping("/price")
@Slf4j
public class PriceController {

    public static final String DATE_FORMAT = "yyyy-MM-dd-HH.mm.ss";
    private final PriceUseCase priceUseCase;
    private PriceDtoMapper priceDtoMapper;

    public PriceController(PriceUseCase priceUseCase, PriceDtoMapper priceDtoMapper) {
        this.priceUseCase = priceUseCase;
        this.priceDtoMapper = priceDtoMapper;
    }

    @GetMapping(value = "/startDate/{startDate}/productId/{productId}/brandId/{brandId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PriceDto> getPrice(@PathVariable("startDate") @NotNull @DateTimeFormat(pattern = DATE_FORMAT) final String startDate,
                                                   @PathVariable("productId") @NotNull final String productId,
                                                   @PathVariable("brandId") @NotNull final String brandId) {
        log.info("[PriceController - method getPrice received request with parameters startDate: {} - productId: {} - brandId: {} ]", startDate,
                productId, brandId);

        PriceDto priceDto = this.priceUseCase.getPrice(startDate, productId, brandId);

        return ResponseEntity.status(HttpStatus.OK).body(priceDto);

    }
}
