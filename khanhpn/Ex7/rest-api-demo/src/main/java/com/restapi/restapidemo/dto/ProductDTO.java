package com.restapi.restapidemo.dto;

import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.entity.Warehouse;
import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Data
public class ProductDTO extends BaseDTO{
    private int id;
    @CodeMustStartWith(value = "P", message = "Code must start with P")
    @NotNull
    private String productCode;
    @NotNull
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private int productQuantity;
    private int productSold;
    private int category;
    private int warehouse;

    public void setCategory(Category categoryEntity) {
        if(categoryEntity !=null) {
            category = categoryEntity.getId();
        } else {
            category = 0;
        }
    }

    public void setWarehouse(Warehouse warehouseEntity) {
        if(warehouseEntity != null){
            warehouse = warehouseEntity.getId();
        } else {
            warehouse = 0;
        }
    }
}
