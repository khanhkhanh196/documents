package com.jdbc.demo.model.mapper;

import com.jdbc.demo.model.Warehouse;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class WarehouseMapper implements RowMapper<Warehouse> {

    @Override
    public Warehouse mapRow(ResultSet rs, int i) throws SQLException {
        Warehouse warehouse = new Warehouse();
        warehouse.setWarehouseId(rs.getInt("warehouse_id"));
        warehouse.setWarehouseCode(rs.getString("warehouse_code"));
        warehouse.setWarehouseName(rs.getString("warehouse_name"));
        warehouse.setLocation(rs.getString("location"));
        warehouse.setCreateDate(rs.getTimestamp("created_date"));
        warehouse.setUpdateDate(rs.getTimestamp("updated_date"));
        return warehouse;
    }
}
