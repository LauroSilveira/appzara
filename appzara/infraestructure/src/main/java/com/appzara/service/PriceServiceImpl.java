package com.appzara.service;

import com.appzara.dto.PriceDto;
import com.appzara.entity.Price;
import com.appzara.exception.ResourceNotFoundException;
import com.appzara.mapper.PriceDtoMapper;
import com.appzara.repository.PriceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final PriceDtoMapper priceDtoMapper;

    public PriceServiceImpl(PriceRepository priceRepository, PriceDtoMapper priceDtoMapper) {
        this.priceRepository = priceRepository;
        this.priceDtoMapper = priceDtoMapper;
    }

    @Override
    public List<PriceDto> getPrice(LocalDateTime date, String productId, String brandId) {
        log.info("PriceServiceImpl - query to priceRepository with parameters - date: {}, productId: {}, " +
                "brandId: {}", date, productId, brandId);

        final List<Price> prices = this.priceRepository.findAllByStartDateIsGreaterThanAndProductIdAndBrandId(date, productId, brandId);

        if(prices.isEmpty()) {
            throw new ResourceNotFoundException("Resource not found");
        }

        return this.priceDtoMapper.mapToPriceDto(prices);
    }
}
