package com.restapi.restapidemo.service;

import com.restapi.restapidemo.dao.daointerface.WarehouseDAO;
import com.restapi.restapidemo.entity.Warehouse;
import com.restapi.restapidemo.service.serviceinterface.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    @Autowired
    private WarehouseDAO warehouseDAO;
    @Override
    public List<Warehouse> getAllWarehouse() {
        return warehouseDAO.getAllWarehouse();
    }

    @Override
    public Warehouse getWarehouse(int theId) {
        return warehouseDAO.getWarehouse(theId);
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        warehouseDAO.saveWarehouse(warehouse);
    }

    @Override
    public void deleteWarehouse(int theId) {
        warehouseDAO.deleteWarehouse(theId);
    }
}
