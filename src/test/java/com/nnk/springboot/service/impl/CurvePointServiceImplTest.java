package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.mapper.CurvePointMapper;
import com.nnk.springboot.repositories.CurvePointRepository;
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
class CurvePointServiceImplTest {

    @Mock
    private CurvePointMapper mapper;
    @Mock
    private CurvePointRepository repository;
    @InjectMocks
    private CurvePointServiceImpl service;


    @Test
    void itShouldFindAll() {
        //GIVEN
        CurvePoint entity = new CurvePoint();
        List<CurvePoint> entityList = List.of(entity);
        CurvePointDto dto = new CurvePointDto();
        List<CurvePointDto> dtoList = List.of(dto);
        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);
        //WHEN
        List<CurvePointDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(entityList);
        assertThat(result).isEqualTo(dtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        CurvePoint entity = new CurvePoint();
        CurvePointDto dto = new CurvePointDto();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        //WHEN
        CurvePointDto result = service.save(dto);
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
        CurvePoint entity = new CurvePoint();
        CurvePointDto dto = new CurvePointDto();
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        //WHEN
        CurvePointDto result = service.update(1,dto);
        //THEN
        verify(repository).save(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        CurvePoint entity = new CurvePoint();
        CurvePointDto dto = new CurvePointDto();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);
        //WHEN
        CurvePointDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(entity);
        assertThat(result).isEqualTo(dto);
    }
}