package com.jdbc.demo.modelJPA;



import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name="product")
public class ProductJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private  int productId;
    @Column(name="product_code")
    private  String productCode;
    @Column(name="category")
    private  int productCategory;
    @Column(name="warehouse")
    private  int productWareHouse;
    @Column(name="product_name")
    private  String productName;
    @Column(name="product_description")
    private  String productDescription;
    @Column(name="image_url")
    private  String productImageUrl;
    @Column(name="quantity")
    private  int productQuantity;
    @Column(name="sold_quantity")
    private  int productSold;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private  Timestamp updatedDate;

    public ProductJPA(String productCode, int productCategory, int productWareHouse, String productName, String productDescription, String productImageUrl, int productQuantity, int productSold, Timestamp createdDate, Timestamp updatedDate) {
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
