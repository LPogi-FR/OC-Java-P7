package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {
    Integer id;
    @NotBlank(message = "MoodysRating is mandatory")
    String moodysRating;
    @NotBlank(message = "SandPRating is mandatory")
    String sandPRating;
    @NotBlank(message = "FitchRating is mandatory")
    String fitchRating;
    @NotNull(message = "Order number is mandatory")
    Integer orderNumber;
}
