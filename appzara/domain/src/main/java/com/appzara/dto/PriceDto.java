package com.appzara.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;

public record PriceDto(long brandId, String productId, int priority, int rate, LocalDateTime startDate,
                       LocalDateTime endDate, BigDecimal amount) {

    public BigDecimal setAmnount(BigDecimal totalAmount) {
        return totalAmount;
    }

}