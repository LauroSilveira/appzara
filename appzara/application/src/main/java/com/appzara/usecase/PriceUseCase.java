package com.appzara.usecase;

import com.appzara.dto.PriceDto;
import com.appzara.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
@Slf4j
public class PriceUseCase {

    private final PriceService priceService;
    private static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public PriceUseCase(PriceService priceService) {
        this.priceService = priceService;
    }

    public List<PriceDto> getPrice(String date, String productId,
                                   String brandId) {
        log.info("PriceUseCase - method getPrice parameters - date: {}, productId: {}, " +
                "brandId: {}", date, productId, brandId);
        final var dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        final var localDateTime = LocalDateTime.parse(date, dateTimeFormatter);
        final var prices = this.priceService.getPrice(localDateTime, productId, brandId);
        return prices.stream().filter(p -> p.priority() == 1).toList();
    }
}
