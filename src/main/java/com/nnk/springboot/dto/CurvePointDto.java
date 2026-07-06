package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CurvePointDto {
    Integer id;

    @NotNull(message = "CurveId must be not null")
    Integer curveId;
    Timestamp asOfDate;
    @NotBlank(message = "Term is mandatory")
    Double term;
    @NotBlank(message = "Value is mandatory")
    Double value;
    Timestamp creationDate;
}
