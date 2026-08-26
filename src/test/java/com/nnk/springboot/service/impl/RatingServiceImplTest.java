package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import com.nnk.springboot.mapper.RatingMapper;
import com.nnk.springboot.repositories.RatingRepository;
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
class RatingServiceImplTest {

    @Mock
    private RatingMapper mapper;
    @Mock
    private RatingRepository repository;
    @InjectMocks
    private RatingServiceImpl service;

    @Test
    void itShouldFindAll() {
        //GIVEN
        Rating entity = new Rating();
        List<Rating> entityList = List.of(entity);
        RatingDto dto = new RatingDto();
        List<RatingDto> dtoList = List.of(dto);
        when(repository.findAll()).thenReturn(entityList);
        when(mapper.toDtoList(entityList)).thenReturn(dtoList);
        //WHEN
        List<RatingDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(entityList);
        assertThat(result).isEqualTo(dtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        Rating entity = new Rating();
        RatingDto dto = new RatingDto();
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        //WHEN
        RatingDto result = service.save(dto);
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
        Rating entity = new Rating();
        RatingDto dto = new RatingDto();
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toEntity(dto)).thenReturn(entity);
        //WHEN
        RatingDto result = service.update(1,dto);
        //THEN
        verify(repository).save(entity);
        assertThat(result).isEqualTo(dto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        Rating entity = new Rating();
        RatingDto dto = new RatingDto();
        when(repository.findById(1)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);
        //WHEN
        RatingDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(entity);
        assertThat(result).isEqualTo(dto);
    }
}