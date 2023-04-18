package com.jdbc.demo.model;



import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product {
    private  int productId;
    private  String productCode;
    private  int productCategory;
    private  int productWareHouse;
    private  String productName;
    private  String productDescription;
    private  String productImageUrl;
    private  int productQuantity;
    private  int productSold;
    private  Timestamp createdDate;
    private  Timestamp updatedDate;

    public Product(String productCode, int productCategory, int productWareHouse, String productName, String productDescription, String productImageUrl, int productQuantity, int productSold, Timestamp createdDate, Timestamp updatedDate) {
        this.productCode = productCode;
        this.productCategory = productCategory;
        this.productWareHouse = productWareHouse;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImageUrl = productImageUrl;
        this.productQuantity = productQuantity;
        this.productSold = productSold;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
