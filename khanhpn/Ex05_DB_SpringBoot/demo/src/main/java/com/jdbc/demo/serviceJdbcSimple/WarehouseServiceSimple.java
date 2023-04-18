package com.jdbc.demo.serviceJdbcSimple;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcSimple.WarehouseDAO;
import com.jdbc.demo.daoJdbcSimple.WarehouseDAO;
import com.jdbc.demo.model.Warehouse;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.WarehouseServiceSimpleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Service
public class WarehouseServiceSimple implements WarehouseServiceSimpleInterface {

    @Autowired
    private WarehouseDAO warehouseDAO;
    @Autowired
    private Printer printer;

    @Override
    public void getAllWarehouse(){
        ArrayList<Warehouse> listWarehouse = (ArrayList<Warehouse>) warehouseDAO.getAllWarehouse();
        if(listWarehouse.isEmpty()){
            printer.print("There is no warehouse");
        } else {
            for(Warehouse warehouse : listWarehouse) {
                printer.print(warehouse.toString());
            }
        }
    }

    @Override
    public void findWarehouseByID(){
        try{
            Scanner sc = new Scanner(System.in);
            printer.print("Enter ID");
            int id = sc.nextInt();
            Warehouse warehouse = warehouseDAO.findWarehouseByID(id);
            if(warehouse!= null) {
                printer.print(warehouse.toString());
            } else{
                printer.print("There is no warehouse with" + id + "ID");
            }

        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public  void deleteWarehouseWithID(){
        try{
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            warehouseDAO.deleteWarehouseByID(index);
            printer.print("Delete success");
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public void addNewWarehouse(){
        String warehouseCode = "";
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter warehouse code");
            warehouseCode = sc.nextLine();
            printer.print("Enter warehouse name");
            sc.nextLine();
            String warehouseName = sc.nextLine();
            printer.print("Enter warehouse location");
            String location = sc.nextLine();

            long date = (new Date().getTime());
            Timestamp createdDate = new Timestamp(date);

            Warehouse warehouse = new Warehouse(warehouseCode, warehouseName,location,createdDate,null);
            warehouseDAO.addWarehouse(warehouse);
        }
        catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+warehouseCode+"' for key 'warehouse.warehouse_code'";
            printer.print(e.getMessage());
            if (e.getCause()!= null && e.getCause().toString().contains(cause)) {
                printer.print("Warehouse code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateWarehouseById(){
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter warehouse id");
            int id = sc.nextInt();
            Warehouse warehouse = warehouseDAO.findWarehouseByID(id);
            if(warehouse!= null) {
                printer.print("Enter warehouse code");
                String warehouseCode = sc.nextLine();
                printer.print("Enter warehouse name");
                sc.nextLine();
                String warehouseName = sc.nextLine();
                printer.print("Enter warehouse location");
                String location = sc.nextLine();

                long date = (new Date().getTime());
                Timestamp updatedDate = new Timestamp(date);

                warehouseDAO.updateWarehouseByID
                        (id,warehouseCode,warehouseName,location,updatedDate);
            } else{
                printer.print("There is no warehouse with" + id + "ID");
            }
        }catch (Exception e) {
                printer.printError(e.getMessage());
        }
    }
}
