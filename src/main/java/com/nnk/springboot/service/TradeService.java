package com.nnk.springboot.service;

import com.nnk.springboot.dto.TradeDto;
import java.util.List;

public interface TradeService {

    List<TradeDto> findAll();

    TradeDto save(TradeDto tradeDto);

    void delete(Integer id);

    TradeDto update(Integer id, TradeDto tradeDto);

    TradeDto findById(Integer id);
}
