package com.restapi.restapidemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="category")
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int id;

    @CodeMustStartWith(value = "C",message = "Code must start with C")
    @NotNull
    @Column(name="category_code")
    private String categoryCode;

    @NotNull
    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_description")
    private String categoryDescription;

    @JsonIgnore
    @OneToMany(targetEntity = Product.class,mappedBy = "category",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<Product> productList;

    public Category(String categoryCode, String categoryName, String categoryDescription) {
        super();
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
    }
}
