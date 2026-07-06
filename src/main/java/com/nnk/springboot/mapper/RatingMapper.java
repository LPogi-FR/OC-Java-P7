package com.nnk.springboot.mapper;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDto;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {
    Rating toEntity(RatingDto ratingDto);
    RatingDto toDto(Rating rating);

    List<Rating> toEntity(List<RatingDto> ratingDtoList);
    List<RatingDto> toDto(List<Rating> ratingList);
}
