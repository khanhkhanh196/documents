package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Warehouse;
import com.jdbc.demo.model.mapper.WarehouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class WarehouseImpl implements WarehouseDAO{

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public List<Warehouse> getAllWarehouse() {
        List<Warehouse> warehouseList = new ArrayList<>();
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("get_all_warehouse")
                .returningResultSet("warehouseList", new WarehouseMapper());
        Map<String,Object> out = jdbcCall.execute();
        warehouseList = (List<Warehouse>) out.get("categoryList");
        return warehouseList;
    }

    @Override
    public Warehouse findWarehouseByID(int id) {
        List<Warehouse> warehouseList = new ArrayList<>();
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("get_warehouse_by_id").returningResultSet("warehouseList", new WarehouseMapper());
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Map<String, Object> execute = jdbcCall.execute(param);
        warehouseList = (ArrayList<Warehouse>) execute.get("warehouseList");
        return warehouseList.get(0);
    }

    @Override
    public int updateWarehouseByID(int warehouseId, String warehouseCode, String warehouseName, String location, Timestamp updateDate) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("update_product_by_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",warehouseId)
                .addValue("warehouse_code",warehouseCode)
                .addValue("warehouse_name",warehouseName)
                .addValue("location",location)
                .addValue("updated_date",updateDate);
        jdbcCall.execute(params);
        return 0;
    }

    @Override
    public int addWarehouse(Warehouse warehouse) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("warehouse");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(warehouse);
        return simpleJdbcInsert.execute(parameters);
    }

    @Override
    public int deleteWarehouseByID(int id) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("delete_warehouse_by_id");
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        jdbcCall.execute(param);
        return 0;
    }
}
