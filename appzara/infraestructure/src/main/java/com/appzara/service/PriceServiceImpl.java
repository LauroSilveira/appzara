package com.appzara.service;

import com.appzara.exception.ResourceNotFoundException;
import com.appzara.dto.PriceDto;
import com.appzara.entity.Price;
import com.appzara.repository.PriceRepository;
import com.appzara.service.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<PriceDto> getPrice(LocalDateTime startDate, String productId, String brandId) {
        log.info("PriceServiceImpl - query to priceRepository with parameters - startDate: {}, productId: {}, " +
                "brandId: {}", startDate, productId, brandId);
        try {
            final List<Price> prices = this.priceRepository.getPrices(startDate, productId, brandId);
            return prices.stream().map(price -> new PriceDto(price.getBrandId(), price.getProductId(),
                            price.getPriority(), price.getRate(), price.getStartDate(), price.getEndDate(), price.getAmount()))
                    .toList();
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("Error consulting query");
        }
    }
}
