package com.nnk.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleNameDto {
    Integer id;
    @NotBlank(message = "Name is mandatory")
    String name;
    @NotBlank(message = "Description is mandatory")
    String description;
    @NotBlank(message = "Json is mandatory")
    String json;
    @NotBlank(message = "Template is mandatory")
    String template;
    @NotBlank(message = "Sql is mandatory")
    String sqlStr;
    @NotBlank(message = "Sql Part is mandatory")
    String sqlPart;
}
