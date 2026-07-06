package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "srping")
public interface RuleNameMapper {
    RuleName toEntity(RuleNameDto ruleNameDto);
    RuleNameDto toDto(RuleName ruleName);

    List<RuleName> toEntity(List<RuleNameDto> ruleNameDtoList);
    List<RuleNameDto> toDto(List<RuleName> ruleNameList);
}
