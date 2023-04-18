package com.restapi.restapidemo.dao.daointerface;

import com.restapi.restapidemo.entity.Warehouse;

import java.util.List;

public interface WarehouseDAO {
    public List<Warehouse> getAllWarehouse();

    public Warehouse getWarehouse(int theId);

    public void saveWarehouse(Warehouse warehouse);

    public void deleteWarehouse(int theId);
}
