package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Warehouse;

import java.sql.Timestamp;
import java.util.List;

public interface WarehouseDAO {
    public List<Warehouse> getAllWarehouse();

    public Warehouse findWarehouseByID(int id);

    public int updateWarehouseByID(int warehouseId, String warehouseCode, String warehouseName, String location, Timestamp updateDate);

    public int addWarehouse(Warehouse warehouse);

    public int deleteWarehouseByID(int id);
}
