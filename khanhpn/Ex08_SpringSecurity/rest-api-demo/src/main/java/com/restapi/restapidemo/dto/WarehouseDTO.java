package com.restapi.restapidemo.dto;

import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Data
public class WarehouseDTO extends BaseDTO{
    private int id;
    @NotNull
    @CodeMustStartWith(value="W", message = "Code must start with W")
    private String warehouseCode;
    @NotNull
    private String warehouseName;
    private String location;
}
