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

    /**
     * Find all trade in database
     * @return List<TradeDto>
     */
    @Override
    public List<TradeDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * Save trade in database
     * @param tradeDto TradeDto
     * @return List<TradeDto>
     */
    @Override
    public TradeDto save(TradeDto tradeDto) {
        repository.save(mapper.toEntity(tradeDto));
        return tradeDto;
    }

    /**
     * Delete trade in database
     * @param id Integer
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Update existing trade in database
     * @param id Integer
     * @param tradeDto TradeDto
     * @return TradeDto
     */
    @Override
    public TradeDto update(Integer id, TradeDto tradeDto) {
        Trade tradeToUpdate = mapper.toEntity(tradeDto);
        tradeToUpdate.setId(id);
        repository.save(tradeToUpdate);
        return tradeDto;
    }

    /**
     * Find trade with specific id in database
     * @param id Integer
     * @return TradeDto
     */
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
