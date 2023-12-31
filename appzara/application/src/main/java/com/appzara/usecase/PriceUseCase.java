package com.appzara.usecase;

import com.appzara.dto.PriceDto;
import com.appzara.service.PriceService;
import com.appzara.usecase.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Slf4j
public class PriceUseCase {

    private final PriceService priceService;
    private static final String DATE_PATTERN = "yyyy-MM-dd-HH.mm.ss";

    public PriceUseCase(PriceService priceService) {
        this.priceService = priceService;
    }

    public PriceDto getPrice(String startDate, String productId,
                                   String brandId) {
        log.info("PriceUseCase - method getPrice parameters - startDate: {}, productId: {}, " +
                "brandId: {}", startDate, productId, brandId);
        final var startDateFormatted = getLocalDateTimeFormatted(startDate);
        final var prices = this.priceService.getPrice(startDateFormatted, productId, brandId);

        if (prices.size() > 1) {
            return Objects.requireNonNull(prices.stream().filter(p -> p.priority() == 1)
                    .max(Comparator.comparing(PriceDto::amount))
                    .orElseThrow(() -> new ResourceNotFoundException("No price found for the date")));
        }
        return prices.stream().findFirst().orElseThrow(() -> new ResourceNotFoundException("No price found for the date"));
    }

    private static LocalDateTime getLocalDateTimeFormatted(final String startDate) {
        try {
            final var dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
            return LocalDateTime.parse(startDate, dateTimeFormatter);
        }catch (DateTimeException ex) {
            throw new DateTimeException("Invalid DateTime formmat");
        }
    }
}
