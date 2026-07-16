package com.nnk.springboot.mapper;

import java.util.List;
import com.nnk.springboot.dto.BidListDto;
import com.nnk.springboot.domain.BidList;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BidListMapper {
    BidList toEntity(BidListDto bidListDto);
    BidListDto toDto(BidList bidList);

    List<BidList> toEntityList(List<BidListDto> bidListDtoList);
    List<BidListDto> toDtoList(List<BidList> bidListList);
}