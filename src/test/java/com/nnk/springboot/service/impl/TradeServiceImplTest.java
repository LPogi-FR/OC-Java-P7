package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.TradeDto;
import com.nnk.springboot.mapper.TradeMapper;
import com.nnk.springboot.repositories.TradeRepository;
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
class TradeServiceImplTest {

    @Mock
    private TradeMapper mapper;
    @Mock
    private TradeRepository repository;
    @InjectMocks
    private TradeServiceImpl service;


    @Test
    void itShouldFindAll() {
        //GIVEN
        Trade entity = new Trade();
        List<Trade> entityList = List.of(entity);
        TradeDto dto = new TradeDto();
        List<TradeDto> dtoList = List.of(dto);
        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);
        //WHEN
        List<TradeDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(entityList);
        assertThat(result).isEqualTo(dtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        Trade entity = new Trade();
        TradeDto dto = new TradeDto();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        //WHEN
        TradeDto result = service.save(dto);
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
        Trade entity = new Trade();
        TradeDto dto = new TradeDto();
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        //WHEN
        TradeDto result = service.update(1,dto);
        //THEN
        verify(repository).save(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        Trade entity = new Trade();
        TradeDto dto = new TradeDto();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);
        //WHEN
        TradeDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(entity);
        assertThat(result).isEqualTo(dto);
    }
}