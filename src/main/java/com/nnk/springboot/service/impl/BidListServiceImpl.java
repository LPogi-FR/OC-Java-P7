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

    /**
     * Find all Bid in database
     * @return List<BidListDto>
     */
    @Override
    public List<BidListDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * Save Bid in database
     * @param bidListDto BidListDto
     * @return List<BidListDto>
     */
    @Override
    public BidListDto save(BidListDto bidListDto) {
        repository.save(mapper.toEntity(bidListDto));
        return bidListDto;
    }
    /**
     * Delete bid in database
     * @param id Integer
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Update existing bid in database
     * @param id Integer
     * @param bidListDto BidListDto
     * @return BidListDto
     */
    @Override
    public BidListDto update(Integer id, BidListDto bidListDto) {
        BidList bidListToUpdate = mapper.toEntity(bidListDto);
        bidListToUpdate.setBidListId(id);
        repository.save(bidListToUpdate);
        return bidListDto;
    }

    /**
     * Find bid with specific id in database
     * @param id Integer
     * @return BidListDto
     */
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