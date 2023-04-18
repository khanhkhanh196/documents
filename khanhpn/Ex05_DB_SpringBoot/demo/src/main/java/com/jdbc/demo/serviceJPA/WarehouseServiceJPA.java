package com.jdbc.demo.serviceJPA;


import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJPA.WarehouseDAOJPA;
import com.jdbc.demo.modelJPA.WarehouseJPA;
import com.jdbc.demo.serviceJPA.serviceInterface.WarehouseServiceJPAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;
import java.util.*;

@Service
@EnableTransactionManagement
public class WarehouseServiceJPA implements WarehouseServiceJPAInterface {
    @Autowired
    private WarehouseDAOJPA warehouseDAO;
    @Autowired
    private Printer printer;

    @Override
    public void getAllWareHouse(){
        List<WarehouseJPA> list = new ArrayList<>();
        list = warehouseDAO.findAll();
        if(!list.isEmpty()) {
            for(WarehouseJPA war : list) {
                printer.print(war.toString());
            }
        } else {
            printer.print("There is no warehouse");
        }

    }

    @Override
    public void getWareHouseByID(){
        try {
            Scanner sc = new Scanner(System.in);
            Optional<WarehouseJPA> warehouseById = warehouseDAO.findById(sc.nextInt());
            if(warehouseById.isEmpty()) {
                printer.print("Warehouse doesn't exist");
            } else {
                printer.print(warehouseById.get().toString());
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid");
        }
    }

    @Override
    public void deleteWereHouseByID(){
        try {
            Scanner sc = new Scanner(System.in);
            warehouseDAO.deleteById(sc.nextInt());
        } catch (Exception e) {
            printer.print("Your entered is invalid");
        }
    }

    @Override
    public void addNewWareHouse(){
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter warehouse code:  ");
            String wareCode = sc.nextLine();
            printer.print("Enter warehouse name: ");
            String wareName = sc.nextLine();
            printer.print("Enter warehouse description: ");
            String wareDes = sc.nextLine();

            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());

            WarehouseJPA war = new WarehouseJPA(wareCode,wareName,wareDes,time,null);
            warehouseDAO.save(war);
        } catch (Exception e){
            String error = "org.hibernate.exception.ConstraintViolationException";
            if(e.getCause().toString().contains(error)){
                printer.print("The warehouse code already existed, please enter another one");
            };
        }

    }

    @Override
    public void updateWarehouseByID() {
        Scanner sc = new Scanner(System.in);
        printer.print("Enter Warehouse ID");
        int id = sc.nextInt();
        Optional<WarehouseJPA> warehouse = warehouseDAO.findById(id);
          if(warehouse.isEmpty()) {
            printer.print("Warehouse doesnt exist");
        } else {
              printer.print("Update warehouse");
              printer.print("Enter warehouse code:");
              sc.nextLine();
              String warehouseCode = sc.nextLine();
              printer.print("Enter warehouse name: ");
              String wareName = sc.nextLine();
              printer.print("Enter warehouse location: ");
              String wareLoc = sc.nextLine();

              Date date = new Date();
              long time = date.getTime();
              Timestamp timestamp = new Timestamp(time);

              WarehouseJPA readyToUpdate = warehouse.get();
              readyToUpdate.setWarehouseCode(warehouseCode);
              readyToUpdate.setWarehouseName(wareName);
              readyToUpdate.setLocation(wareLoc);
              readyToUpdate.setUpdateDate(timestamp);
              warehouseDAO.save(readyToUpdate);
              printer.print("Update success");
        }
    }
}
