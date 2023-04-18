package com.restapi.restapidemo.dto;

import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CategoryDTO extends BaseDTO {
    private int id;
    @CodeMustStartWith(value = "C",message = "Code must start with C")
    @NotNull(message = "value must not null")
    private String categoryCode;
    @NotNull(message = "value must not null")
    private String categoryName;
    private String categoryDescription;
}
