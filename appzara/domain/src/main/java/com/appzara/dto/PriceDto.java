package com.appzara.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceDto(String brandId, String productId, int priority, int rate, LocalDateTime startDate,
                       LocalDateTime endDate, BigDecimal amount, String currency) {

}
