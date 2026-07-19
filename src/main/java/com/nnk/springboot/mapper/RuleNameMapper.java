package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RuleNameMapper {
    RuleName toEntity(RuleNameDto ruleNameDto);
    RuleNameDto toDto(RuleName ruleName);

    List<RuleName> toEntityList(List<RuleNameDto> ruleNameDtoList);
    List<RuleNameDto> toDtoList(List<RuleName> ruleNameList);
}
