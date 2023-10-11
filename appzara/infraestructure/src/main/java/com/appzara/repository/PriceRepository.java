package com.appzara.repository;

import com.appzara.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Query("SELECT p FROM Price p WHERE p.startDate >= :date OR p.endDate < :date AND p.productId = :productId AND p.brandId = :brandId " +
            "ORDER BY p.amount")
    List<Price> getPrices(LocalDateTime date, String productId, String brandId);
}
