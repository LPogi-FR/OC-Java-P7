package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TradeMapper {
    Trade toEntity(TradeDto tradeDto);
    TradeDto toDto(Trade trade);

    List<Trade> toEntityList(List<TradeDto> tradeDtoList);
    List<TradeDto> toDtoList(List<Trade> tradeList);
}
