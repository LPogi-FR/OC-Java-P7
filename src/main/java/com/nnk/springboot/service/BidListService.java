package com.nnk.springboot.service;

import com.nnk.springboot.controllers.dto.BidListDto;

import java.util.List;

public interface BidListService {
    List<BidListDto> findAll();

    BidListDto save(BidListDto bidListDto);

    void delete(Integer id);

    BidListDto update(Integer id, BidListDto bidListDto);
    BidListDto findById(Integer id);
}

