package com.restapi.restapidemo.entity;


import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Entity
@Table(name="product")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_id")
    private int id;

    @NotNull
    @CodeMustStartWith(value="P",message = "Code must start with P")
    @Column(name="product_code")
    private String productCode;

    @NotNull
    @Column(name="product_name")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Column(name="image_url")
    private String productImageUrl;

    @Column(name="quantity")
    private int productQuantity;

    @Column(name="sold_quantity")
    private int productSold;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="category_detail_id")
    private Category category;

    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="warehouse_detail_id")
    private Warehouse productWareHouse;

    public Product(String productCode, Category category, Warehouse productWareHouse, String productName, String productDescription, String productImageUrl, int productQuantity, int productSold) {
        super();
        this.productCode = productCode;
        this.category = category;
        this.productWareHouse = productWareHouse;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productImageUrl = productImageUrl;
        this.productQuantity = productQuantity;
        this.productSold = productSold;
    }

    public void setCategory(int theId){
        Category newCategory = new Category();
        newCategory.setId(theId);
        category = newCategory;
    }

    public void setWarehouse(int theId){
        Warehouse newWarehouse = new Warehouse();
        newWarehouse.setId(theId);
        productWareHouse = newWarehouse;
    }

}
