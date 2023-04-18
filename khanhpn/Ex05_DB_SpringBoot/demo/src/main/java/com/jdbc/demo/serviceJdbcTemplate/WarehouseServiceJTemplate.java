package com.jdbc.demo.serviceJdbcTemplate;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcTemplate.WarehouseDAO;
import com.jdbc.demo.model.Warehouse;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.WarehouseServiceTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Service
public class WarehouseServiceJTemplate implements WarehouseServiceTemplateInterface {

    @Autowired
    private WarehouseDAO warehouseDAO;
    @Autowired
    private Printer printer;

    @Override
    public void getAllWarehouse(){
        ArrayList<Warehouse> list = (ArrayList<Warehouse>) warehouseDAO.getAllWarehouse();
        if(list.isEmpty()) {
            printer.print("There is no warehouse");
        } else {
            for(Warehouse warehouse : list) {
                printer.print(warehouse.toString());
            }
        }
    }

    @Override
    public void findWarehouseById(){
        try{
            Scanner sc = new Scanner(System.in);
            printer.print("Enter ID");
            int id = sc.nextInt();
            printer.print(warehouseDAO.findWarehouseByID(id).toString());
        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public void addNewWarehouse(){
        String warehouseCode = "";
        try {
            Scanner scUpdate = new Scanner(System.in);
            printer.print("Enter new Warehouse code");
            warehouseCode = scUpdate.nextLine();
            printer.print("Enter new Warehouse name");
            scUpdate.nextLine();
            String warehouseName = scUpdate.nextLine();
            printer.print("Enter warehouse location");
            String location = scUpdate.nextLine();
            long date = (new Date().getTime());
            Timestamp datetime = new Timestamp(date);

            int check = warehouseDAO.addWarehouse(warehouseCode, warehouseName, location, datetime);
            if(check == 1) {
                printer.print("Add success");
            } else {
                printer.print("Add failed, Warehouse code existed");
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+warehouseCode+"' for key 'warehouse.warehouse_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Warehouse code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateWarehouseById(){
        String warehouseCode = "";
        try{
            Scanner scUpdate = new Scanner(System.in);
            printer.print("Enter ID");
            int theID = scUpdate.nextInt();
            printer.print("Enter new Warehouse code");
            warehouseCode = scUpdate.nextLine();
            printer.print("Enter new Warehouse name");
            scUpdate.nextLine();
            String warehouseName = scUpdate.nextLine();
            printer.print("Enter warehouse location");
            String description = scUpdate.nextLine();
            long date = (new Date().getTime());
            Timestamp datetime = new Timestamp(date);
            int check = warehouseDAO.updateWarehouseByID(theID, warehouseCode, warehouseName, description, datetime);
            if(check == 1) {
                printer.print("Update success");
            } else {
                printer.print("Update failed, Warehouse code duplicated");
            }

        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+warehouseCode+"' for key 'warehouse.warehouse_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Warehouse code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void deleteWarehouseByID() {
        try{
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            int check = warehouseDAO.deleteWarehouseByID(index);
            if(check == 1) {
                printer.print("Delete success");
            } else {
                printer.print("Delete failed, You entered invalid index");
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
        }
    }
}
