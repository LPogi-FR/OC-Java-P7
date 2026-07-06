package com.nnk.springboot.service;

import com.nnk.springboot.dto.RuleNameDto;
import java.util.List;

public interface RuleNameService {

    List<RuleNameDto> findAll();

    RuleNameDto save(RuleNameDto tradeDto);

    void delete(Integer id);

    RuleNameDto update(Integer id, RuleNameDto tradeDto);

    RuleNameDto findById(Integer id);
}
