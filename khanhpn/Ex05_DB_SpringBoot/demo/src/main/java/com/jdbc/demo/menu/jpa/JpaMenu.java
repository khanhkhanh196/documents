package com.jdbc.demo.menu.jpa;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.serviceJPA.serviceInterface.CategoryServiceJPAInterface;
import com.jdbc.demo.serviceJPA.serviceInterface.ProductServiceJPAInterface;
import com.jdbc.demo.serviceJPA.serviceInterface.WarehouseServiceJPAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class JpaMenu {
    
    @Autowired
    CategoryServiceJPAInterface categoryService;
    
    @Autowired
    ProductServiceJPAInterface productService;
    
    @Autowired
    WarehouseServiceJPAInterface warehouseService;

    @Autowired
    Printer printer;
    public void showMenuCategoryJPA(){
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
                    categoryService.getAllCategory();
                    break;
                case 2:
                    printer.print("Category by id");
                    categoryService.getCategoryByID();
                    break;
                case 3:
                    printer.print("Add a new Category");
                    categoryService.addNewCategory();
                    break;
                case 4:
                    printer.print("Delete a category");
                    categoryService.deleteCategoryByID();
                    break;
                case 5:
                    printer.print("Update a category");
                    categoryService.updateCategoryByID();
                case 6:
                    check1 = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void showMenuProductJPA(){
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
                    productService.getAllProduct();
                    break;
                case 2:
                    productService.getProductByID();
                    break;
                case 3:
                    printer.print("Add a new product");
                    productService.addNewProduct();
                    break;
                case 4:
                    printer.print("Delete a product");
                    productService.deleteProductID();
                    break;
                case 5:
                    printer.print("Update a product");
                    productService.updateProductByID();
                    break;
                case 6:
                    check = false;
                    break;
                default:
                    break;
            }
        }
    }

    public void showMenuWarehouseJPA(){
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
                    warehouseService.getAllWareHouse();
                    break;
                case 2:
                    warehouseService.getWareHouseByID();
                    break;
                case 3:
                    printer.print("Add a new warehouse");
                    warehouseService.addNewWareHouse();
                    break;
                case 4:
                    printer.print("Delete a warehouse");
                    warehouseService.deleteWereHouseByID();
                    break;
                case 5:
                    printer.print("Update a warehouse");
                    warehouseService.updateWarehouseByID();
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
