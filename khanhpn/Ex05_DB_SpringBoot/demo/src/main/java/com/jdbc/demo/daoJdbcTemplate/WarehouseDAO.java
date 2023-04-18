package com.jdbc.demo.daoJdbcTemplate;

import com.jdbc.demo.model.Category;
import com.jdbc.demo.model.Warehouse;

import java.sql.Timestamp;
import java.util.List;

public interface WarehouseDAO {

    public List<Warehouse> getAllWarehouse();

    public Warehouse findWarehouseByID(int id);

    public int updateWarehouseByID(int warehouseId, String warehouseCode, String warehouseName, String location, Timestamp updateDate);

    public int addWarehouse(String warehouseCode, String warehouseName, String location, Timestamp createDate);

    public int deleteWarehouseByID(int id);
}
