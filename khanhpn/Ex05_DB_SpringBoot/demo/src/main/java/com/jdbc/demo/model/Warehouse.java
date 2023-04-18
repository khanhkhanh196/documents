package com.jdbc.demo.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ToString
public class Warehouse {
    private  int warehouseId;
    private  String warehouseCode;
    private  String warehouseName;
    private  String location;
    private  Timestamp createDate;
    private  Timestamp updateDate;

    public Warehouse(String warehouseCode, String warehouseName, String location, Timestamp createDate, Timestamp updateDate) {
        this.warehouseCode = warehouseCode;
        this.warehouseName = warehouseName;
        this.location = location;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }
}
