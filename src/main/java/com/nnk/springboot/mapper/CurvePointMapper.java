package com.nnk.springboot.mapper;

import com.nnk.springboot.dto.CurvePointDto;
import com.nnk.springboot.domain.CurvePoint;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CurvePointMapper {

    CurvePoint toEntity(CurvePointDto curvePointDto);
    CurvePointDto toDto(CurvePoint curvePoint);

    List<CurvePoint> toEntityList(List<CurvePointDto> curvePointDtoList);
    List<CurvePointDto> toDtoList(List<CurvePoint> curvePointList);
}
