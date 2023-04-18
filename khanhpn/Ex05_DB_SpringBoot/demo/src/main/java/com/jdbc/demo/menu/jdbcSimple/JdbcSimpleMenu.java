package com.jdbc.demo.menu.jdbcSimple;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.CategoryServiceSimpleInterface;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.ProductServiceSimpleInterface;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.WarehouseServiceSimpleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class JdbcSimpleMenu {
    @Autowired
    private ProductServiceSimpleInterface productSimple;
    @Autowired
    private CategoryServiceSimpleInterface categorySimple;
    @Autowired
    private WarehouseServiceSimpleInterface warehouseSimple;
    @Autowired
    private Printer printer;

    public void showMenuCategory() {
        boolean check1 = true;
        while(check1) {
            printer.print("1. List of categories");
            printer.print("2. Find Category by ID");
            printer.print("3. Add a new Category");
            printer.print("4. Delete a category");
            printer.print("5. Update a category");
            printer.print("6. Back");
            printer.print("Enter your choice: ");
            int subCase = 0;
            try {
                Scanner scc = new Scanner(System.in);
                subCase = scc.nextInt();
            }catch (Exception e) {
                printer.print("Your entered is invalid, Please try again");
            }

            switch (subCase) {
                case 1:
                    printer.print("List of categories: ");
                    categorySimple.getAllCategory();
                    break;
                case 2:
                    printer.print("Category by id");
                    categorySimple.findCategoryByID();
                    break;
                case 3:
                    printer.print("Add a new Category");
                    categorySimple.addNewCategory();
                    break;
                case 4:
                    printer.print("Delete a category");
                    categorySimple.deleteCategoryWithID();
                    break;
                case 5:
                    printer.print("Update a category");
                    categorySimple.updateCategoryById();
                case 6:
                    check1 = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void showMenuProduct(){
        boolean check = true;
        while(check) {
            printer.print("1. List of products");
            printer.print("2. Find a product by ID");
            printer.print("3. Add a new product");
            printer.print("4. Delete a product");
            printer.print("5. Update a product");
            printer.print("6. Back");
            printer.print("Enter your choice: ");
            int subCase = 0;
            try {
                Scanner scc = new Scanner(System.in);
                subCase = scc.nextInt();
            } catch (Exception e) {
                printer.print("Your entered is invalid, Please try again");
            }

            switch (subCase) {
                case 1:
                    printer.print("List of products: ");
                    productSimple.getAllProduct();
                    break;
                case 2:
                    productSimple.findProductByID();
                    break;
                case 3:
                    printer.print("Add a new product");
                    productSimple.addNewProduct();
                    break;
                case 4:
                    printer.print("Delete a product");
                    productSimple.deleteProductWithID();
                    break;
                case 5:
                    printer.print("Update a product");
                    productSimple.updateProductById();
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void showMenuWarehouse(){
        boolean check = true;
        while(check) {
            printer.print("1. List of warehouse");
            printer.print("2. Find a warehouse by ID");
            printer.print("3. Add a new warehouse");
            printer.print("4. Delete a warehouse");
            printer.print("5. Update a warehouse");
            printer.print("6. Back");
            printer.print("Enter your choice: ");
            int subCase = 0;
            try {
                Scanner scc = new Scanner(System.in);
                subCase = scc.nextInt();
            }catch (Exception e) {
                printer.print("Your entered is invalid, Please try again");
            }

            switch (subCase) {
                case 1:
                    printer.print("List of warehouse: ");
                    warehouseSimple.getAllWarehouse();
                    break;
                case 2:
                    warehouseSimple.findWarehouseByID();
                    break;
                case 3:
                    printer.print("Add a new warehouse");
                    warehouseSimple.addNewWarehouse();
                    break;
                case 4:
                    printer.print("Delete a warehouse");
                    warehouseSimple.deleteWarehouseWithID();
                    break;
                case 5:
                    printer.print("Update a warehouse");
                    warehouseSimple.updateWarehouseById();
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }
}
