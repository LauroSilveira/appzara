package com.appzara.repository;

import com.appzara.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findAllByStartDateIsGreaterThanAndProductIdAndBrandId(LocalDateTime startDate, String productId, String brandId);
}
