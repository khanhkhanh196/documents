package com.restapi.restapidemo.entity;




import com.restapi.restapidemo.validation.CodeMustStartWith;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="warehouse")
@Getter
@Setter
@NoArgsConstructor
public class Warehouse extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private int id;

    @NotNull
    @CodeMustStartWith(value="W", message = "Code must start with W")
    @Column(name = "warehouse_code")
    private String warehouseCode;

    @NotNull
    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "location")
    private String location;


    public Warehouse(String warehouseCode, String warehouseName, String location) {
        super();
        this.warehouseCode = warehouseCode;
        this.warehouseName = warehouseName;
        this.location = location;
    }

}