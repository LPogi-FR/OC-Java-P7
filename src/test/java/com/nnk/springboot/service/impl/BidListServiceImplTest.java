package com.nnk.springboot.service.impl;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.mapper.BidListMapper;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BidListServiceImplTest {
    @Mock
    private BidListMapper mapper;
    @Mock
    private BidListRepository repository;
    @InjectMocks
    private BidListServiceImpl service;

    @Test
    void itShouldFindAll() {
        //GIVEN
        BidList bidList = new BidList();
        List<BidList> bidLists = List.of(bidList);
        BidListDto bidListDto = new BidListDto();
        List<BidListDto> bidListDtoList = List.of(bidListDto);
        when(repository.findAll()).thenReturn(bidLists);
        when(mapper.toDtoList(bidLists)).thenReturn(bidListDtoList);
        //WHEN
        List<BidListDto> result = service.findAll();
        //THEN
        verify(repository).findAll();
        verify(mapper).toDtoList(bidLists);
        assertThat(result).isEqualTo(bidListDtoList);
    }

    @Test
    void itShouldSave() {
        //GIVEN
        BidList bidList = new BidList();
        BidListDto bidListDto = new BidListDto();
        when(mapper.toEntity(bidListDto)).thenReturn(bidList);
        when(repository.save(bidList)).thenReturn(bidList);
        //WHEN
        BidListDto result = service.save(bidListDto);
        //THEN
        verify(repository).save(bidList);
        verify(mapper).toEntity(bidListDto);
        assertThat(result).isEqualTo(bidListDto);
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
        BidList bidList = new BidList();
        BidListDto bidListDto = new BidListDto();
        when(repository.save(bidList)).thenReturn(bidList);
        when(mapper.toEntity(bidListDto)).thenReturn(bidList);
        //WHEN
        BidListDto result = service.update(1, bidListDto);
        //THEN
        verify(repository).save(bidList);
        assertThat(result).isEqualTo(bidListDto);
    }

    @Test
    void itShouldFindById() {
        //GIVEN
        BidList bidList = new BidList();
        BidListDto bidListDTO = new BidListDto();
        when(repository.findById(1)).thenReturn(Optional.of(bidList));
        when(mapper.toDto(bidList)).thenReturn(bidListDTO);
        //WHEN
        BidListDto result = service.findById(1);
        //THEN
        verify(repository).findById(1);
        verify(mapper).toDto(bidList);
        assertThat(result).isEqualTo(bidListDTO);
    }
}