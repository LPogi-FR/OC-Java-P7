package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "Order number is mandatory")
    Integer orderNumber;
}
