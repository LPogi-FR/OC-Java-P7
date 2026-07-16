package com.nnk.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.service.BidListService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BidListServiceImpl implements BidListService {

    private final BidListRepository repository;
    private final BidListMapper mapper;

    @Override
    public List<BidListDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public BidListDto save(BidListDto bidListDto) {
        repository.save(mapper.toEntity(bidListDto));
        return bidListDto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public BidListDto update(Integer id, BidListDto bidListDto) {
        BidList bidListToUpdate = mapper.toEntity(bidListDto);
        bidListToUpdate.setBidListId(id);
        repository.save(bidListToUpdate);
        return bidListDto;
    }

    @Override
    public BidListDto findById(Integer id) {
        Optional<BidList> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}