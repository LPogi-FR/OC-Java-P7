package com.nnk.springboot.dto;

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
    Integer curveId;
    Timestamp asOfDate;
    @NotNull(message = "Term is mandatory")
    Double term;
    @NotNull(message = "Value is mandatory")
    Double value;
    Timestamp creationDate;
}
