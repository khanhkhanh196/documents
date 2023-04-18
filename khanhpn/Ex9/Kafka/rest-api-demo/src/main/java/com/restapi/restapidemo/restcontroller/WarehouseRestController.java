package com.restapi.restapidemo.restcontroller;

import com.restapi.restapidemo.dto.WarehouseDTO;
import com.restapi.restapidemo.dto.converter.WarehouseDTOConverter;
import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.entity.Warehouse;
import com.restapi.restapidemo.exception.WarehouseException.WarehouseNotFoundException;
import com.restapi.restapidemo.service.serviceinterface.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WarehouseRestController {

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private WarehouseDTOConverter converter;
    @GetMapping("/warehouse")
    public List<WarehouseDTO> getAllWarehouse(){
        List<WarehouseDTO> warehouseDTOList = new ArrayList<>();
        List<Warehouse> allWarehouse = warehouseService.getAllWarehouse();
        for(Warehouse warehouse: allWarehouse) {
            warehouseDTOList.add(converter.convertToWarehouseDTO(warehouse));
        }
        return warehouseDTOList;
    }

    @GetMapping("/warehouse/{id}")
    public WarehouseDTO getSingleWarehouse(@PathVariable int id) {
        Warehouse warehouse = warehouseService.getWarehouse(id);
        if(warehouse == null) {
            throw new WarehouseNotFoundException("Warehouse not found " + id);
        }
        WarehouseDTO warehouseDTO = converter.convertToWarehouseDTO(warehouse);
        return warehouseDTO;
    }

    @PutMapping("/warehouse/{id}")
    public void updateWarehouse(@PathVariable int id,@Valid @RequestBody WarehouseDTO warehouseDTO) {
        Warehouse existedWarehouse = warehouseService.getWarehouse(id);
        if(existedWarehouse!=null && existedWarehouse.getId() == warehouseDTO.getId()) {
            Warehouse warehouse = null;
            try {
                warehouse = converter.convertWarehouseDtoToEntity(warehouseDTO);
            } catch (ParseException e) {
                e.printStackTrace();
            }
                warehouseService.saveWarehouse(warehouse);
        } else {
            throw new WarehouseNotFoundException("Warehouse not found " + id);
        }
    }
    @PostMapping("/warehouse")
    public void addNewWarehouse(@Valid @RequestBody WarehouseDTO warehouseDTO) {
        Warehouse warehouse = null;
        try {
            warehouse = converter.convertWarehouseDtoToEntity(warehouseDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
            warehouse.setId(0);
            warehouseService.saveWarehouse(warehouse);

    }
    @DeleteMapping("/Warehouses/{id}")
    public void deleteWarehouse(@PathVariable int id) {
        Warehouse existedWarehouse = warehouseService.getWarehouse(id);
        if(existedWarehouse!=null) {
            warehouseService.deleteWarehouse(id);
        } else {
            throw new WarehouseNotFoundException("Warehouse not found " + id);
        }
    }
}
