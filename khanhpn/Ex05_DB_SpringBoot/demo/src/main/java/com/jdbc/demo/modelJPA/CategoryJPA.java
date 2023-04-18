package com.jdbc.demo.modelJPA;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@ToString
@Table(name="category")
public class CategoryJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_id")
    private int categoryID;
    @NotNull
    @Column(name="category_code")
    private String categoryCode;
    @Column(name= "category_name")
    private String categoryName;
    @Column(name="category_description")
    private String categoryDescription;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private Timestamp updatedDate;

    public CategoryJPA(String categoryCode, String categoryName, String categoryDescription, Timestamp createdDate, Timestamp updatedDate) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
