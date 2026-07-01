package com.nnk.springboot.mapper;

import java.util.List;

import com.nnk.springboot.controllers.dto.BidListDto;
import com.nnk.springboot.domain.BidList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "srping")
public interface BidListMapper {
    BidList toEntity(BidListDto bidListDto);
    BidListDto toDto(BidList bidList);

    List<BidList> toEntity(List<BidListDto> bidListDtoList);
    List<BidListDto> toDto(List<BidList> bidListList);
}