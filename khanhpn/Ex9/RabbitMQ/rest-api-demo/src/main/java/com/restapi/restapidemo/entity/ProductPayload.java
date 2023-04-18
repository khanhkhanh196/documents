package com.restapi.restapidemo.entity;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="product_payload")
@ToString
@Data
public class ProductPayload implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id")
    private int id;
    @Column(name="sold_quantity")
    private int productSold;
    @Column(name="created_date")
    private Timestamp createdDate;
    @Column(name="updated_date")
    private Timestamp updatedDate;
}
