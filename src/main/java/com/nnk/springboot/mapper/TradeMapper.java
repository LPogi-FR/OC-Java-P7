package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "srping")
public interface TradeMapper {
    Trade toEntity(TradeDto tradeDto);
    TradeDto toDto(Trade trade);

    List<Trade> toEntity(List<TradeDto> tradeDtoList);
    List<TradeDto> toDto(List<Trade> tradeList);
}
