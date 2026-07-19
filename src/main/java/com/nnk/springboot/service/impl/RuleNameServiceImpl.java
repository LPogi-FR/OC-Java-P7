package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.exception.IdNotFoundException;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.service.RuleNameService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RuleNameServiceImpl implements RuleNameService {

    private final RuleNameMapper mapper;
    private final RuleNameRepository repository;

    @Override
    public List<RuleNameDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    @Override
    public RuleNameDto save(RuleNameDto ruleNameDto) {
        repository.save(mapper.toEntity(ruleNameDto));
        return ruleNameDto;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public RuleNameDto update(Integer id, RuleNameDto ruleNameDto) {
        RuleName ruleNameToUpdate = mapper.toEntity(ruleNameDto);
        ruleNameToUpdate.setId(id);
        repository.save(ruleNameToUpdate);
        return ruleNameDto;
    }

    @Override
    public RuleNameDto findById(Integer id) {
        Optional<RuleName> optionnalEntity = repository.findById(id);
        if (optionnalEntity.isPresent()) {
            return mapper.toDto(optionnalEntity.get());
        } else {
            throw new IdNotFoundException("Id not found");
        }
    }
}
