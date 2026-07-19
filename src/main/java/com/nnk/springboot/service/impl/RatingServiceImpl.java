package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    private final RatingMapper mapper;
    private final RatingRepository repository;

    @Override
    public List<RatingDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public RatingDto save(RatingDto ratingDto) {
        repository.save(mapper.toEntity(ratingDto));
        return ratingDto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public RatingDto update(Integer id, RatingDto ratingDto) {
        Rating ratingToUpdate = mapper.toEntity(ratingDto);
        ratingToUpdate.setId(id);
        repository.save(ratingToUpdate);
        return ratingDto;
    }

    @Override
    public RatingDto findById(Integer id) {
        Optional<Rating> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
