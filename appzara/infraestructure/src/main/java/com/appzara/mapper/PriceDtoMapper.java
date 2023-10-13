package com.appzara.mapper;

import com.appzara.dto.PriceDto;
import com.appzara.entity.Price;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceDtoMapper {
    List<PriceDto> mapToPriceDto(List<Price> priceDtoMapper);
}
