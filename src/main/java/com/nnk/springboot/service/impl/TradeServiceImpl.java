package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TradeServiceImpl implements TradeService {

    private final TradeMapper mapper;
    private final TradeRepository repository;

    @Override
    public List<TradeDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public TradeDto save(TradeDto tradeDto) {
        repository.save(mapper.toEntity(tradeDto));
        return tradeDto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public TradeDto update(Integer id, TradeDto tradeDto) {
        Trade tradeToUpdate = mapper.toEntity(tradeDto);
        tradeToUpdate.setTradeId(id);
        repository.save(tradeToUpdate);
        return tradeDto;
    }

    @Override
    public TradeDto findById(Integer id) {
        Optional<Trade> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
