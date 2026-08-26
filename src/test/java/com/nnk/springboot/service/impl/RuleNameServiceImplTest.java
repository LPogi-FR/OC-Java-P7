package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDto;
import com.nnk.springboot.mapper.RuleNameMapper;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RuleNameServiceImplTest {

    @Mock
    private RuleNameMapper mapper;
    @Mock
    private RuleNameRepository repository;
    @InjectMocks
    private RuleNameServiceImpl service;


    @Test
    void itShouldFindAll() {
        //GIVEN
        RuleName entity = new RuleName();
        List<RuleName> entityList = List.of(entity);
        RuleNameDto dto = new RuleNameDto();
        List<RuleNameDto> dtoList = List.of(dto);
        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);
        //WHEN
        List<RuleNameDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(entityList);
        assertThat(result).isEqualTo(dtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        RuleName entity = new RuleName();
        RuleNameDto dto = new RuleNameDto();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        //WHEN
        RuleNameDto result = service.save(dto);
        //THEN
        verify(repository).save(entity);
        verify(mapper).toEntity(dto);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldDelete() {
        //GIVEN
        //WHEN
        service.delete(1);
        //THEN
        verify(repository).deleteById(1);
    }

    @Test
    void itShouldUpdate() {
        //GIVEN
        RuleName entity = new RuleName();
        RuleNameDto dto = new RuleNameDto();
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        //WHEN
        RuleNameDto result = service.update(1,dto);
        //THEN
        verify(repository).save(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        RuleName entity = new RuleName();
        RuleNameDto dto = new RuleNameDto();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);
        //WHEN
        RuleNameDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(entity);
        assertThat(result).isEqualTo(dto);
    }
}