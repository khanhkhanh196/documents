package com.restapi.restapidemo.dto.converter;

import com.restapi.restapidemo.dto.WarehouseDTO;
import com.restapi.restapidemo.entity.Warehouse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class WarehouseDTOConverter {
    @Autowired
    private ModelMapper modelMapper;

    public WarehouseDTO convertToWarehouseDTO(Warehouse warehouse) {
        WarehouseDTO dto = modelMapper.map(warehouse,WarehouseDTO.class);
        return dto;
    }

    public Warehouse convertWarehouseDtoToEntity(WarehouseDTO warehouseDTO) throws ParseException {
        Warehouse warehouse = modelMapper.map(warehouseDTO,Warehouse.class);
        return warehouse;
    }
}
