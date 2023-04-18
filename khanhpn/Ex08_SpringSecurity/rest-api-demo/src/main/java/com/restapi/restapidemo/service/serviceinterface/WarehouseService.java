package com.restapi.restapidemo.service.serviceinterface;

import com.restapi.restapidemo.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    public List<Warehouse> getAllWarehouse();

    public Warehouse getWarehouse(int theId);

    public void saveWarehouse(Warehouse warehouse);

    public void deleteWarehouse(int theId);
}
