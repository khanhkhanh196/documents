package com.jdbc.demo.model;


import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Category {
    private int categoryID;
    private String categoryCode;
    private String categoryName;
    private String categoryDescription;
    private Timestamp createdDate;
    private Timestamp updatedDate;

    public Category(String categoryCode, String categoryName, String categoryDescription, Timestamp createdDate, Timestamp updatedDate) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
