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

    /**
     * Find all rulename in database
     * @return List<RuleNameDto>
     */
    @Override
    public List<RuleNameDto> findAll() {
        return mapper.toDtoList(repository.findAll());
    }

    /**
     * Save rulename in database
     * @param ruleNameDto RuleNameDto
     * @return List<RuleNameDto>
     */
    @Override
    public RuleNameDto save(RuleNameDto ruleNameDto) {
        repository.save(mapper.toEntity(ruleNameDto));
        return ruleNameDto;
    }

    /**
     * Delete rulename in database
     * @param id Integer
     */
    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    /**
     * Update existing rulename in database
     * @param id Integer
     * @param ruleNameDto RuleNameDto
     * @return RuleNameDto
     */
    @Override
    public RuleNameDto update(Integer id, RuleNameDto ruleNameDto) {
        RuleName ruleNameToUpdate = mapper.toEntity(ruleNameDto);
        ruleNameToUpdate.setId(id);
        repository.save(ruleNameToUpdate);
        return ruleNameDto;
    }

    /**
     * Find rulename with specific id in database
     * @param id Integer
     * @return RuleNameDto
     */
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
