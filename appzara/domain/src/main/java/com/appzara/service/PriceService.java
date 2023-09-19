package com.appzara.service;

import com.appzara.dto.PriceDto;
import org.springframework.cglib.core.Local;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface PriceService {

    List<PriceDto> getPrice(LocalDateTime startDate, LocalDateTime endDate, String productId, String brandId);
}
