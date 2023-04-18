package com.jdbc.demo.daoJdbcTemplate;

import com.jdbc.demo.model.Product;
import com.jdbc.demo.model.Warehouse;
import com.jdbc.demo.model.mapper.ProductMapper;
import com.jdbc.demo.model.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WarehouseDAOImplTemplate implements WarehouseDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Warehouse> getAllWarehouse() {
        String sql = "SELECT * FROM WAREHOUSE";
        List<Warehouse> warehouseList = new ArrayList<Warehouse>();
        warehouseList = jdbcTemplate.query(sql, new WarehouseMapper());

        return warehouseList;
    }

    @Override
    public Warehouse findWarehouseByID(int id) {
        Warehouse warehouse = new Warehouse();
        String sql = "select * from warehouse where warehouse_id=?";
        warehouse = jdbcTemplate.queryForObject(sql,new Object[]{id}, new WarehouseMapper());

        return warehouse;
    }

    @Override
    public int updateWarehouseByID(int warehouseId, String warehouseCode, String warehouseName, String location, Timestamp updateDate) {
        String sql = "update warehouse set " +
                "warehouse_code=?"+"," +
                "warehouse_name=?"+"," +
                "location=?"+","+
                "updated_date=?" +
                "where warehouse_id=?";
        return jdbcTemplate.update(sql,new Object[]{warehouseCode,warehouseName,location,updateDate,updateDate,warehouseId});
    }

    @Override
    public int addWarehouse(String warehouseCode, String warehouseName, String location, Timestamp createDate) {
        String sql = "insert into `warehouse` (`warehouse_code`,`warehouse_name`,`location`,`created_date`,`updated_date`) values\n" +
                "(?,?, ?,?,?),";
        return jdbcTemplate.update(sql,new Object[]{ warehouseCode,warehouseName,location, createDate,null});
    }

    @Override
    public int deleteWarehouseByID(int id) {
        String sql = "delete from warehouse where warehouse_id=?";
        return jdbcTemplate.update(sql,new Object[]{id});
    }
}
