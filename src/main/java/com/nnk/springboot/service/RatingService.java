package com.nnk.springboot.service;

import com.nnk.springboot.dto.RatingDto;
import java.util.List;

public interface RatingService {

    List<RatingDto> findAll();

    RatingDto save(RatingDto ratingDto);

    void delete(Integer id);

    RatingDto update(Integer id, RatingDto tradeDto);

    RatingDto findById(Integer id);
}
