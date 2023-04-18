package com.jdbc.demo.menu.jdbcTemplate;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.CategoryServiceTemplateInterface;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.ProductServiceTemplateInterface;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.WarehouseServiceTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class JdbcTemplateMenu {
    @Autowired
    private CategoryServiceTemplateInterface categoryService;
    
    @Autowired
    private ProductServiceTemplateInterface productService;
    
    @Autowired
    private WarehouseServiceTemplateInterface warehouseService;

    @Autowired
    private Printer printer;
    
    public void showMenuCategory(){
        boolean check = true;
        while(check) {
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
                    categoryService.findCategoryById();
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
                    categoryService.updateCategoryById();
                case 6:
                    check = false;
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
                    productService.getAllProduct();
                    break;
                case 2:
                    productService.findProductById();
                    break;
                case 3:
                    printer.print("Add a new product");
                    productService.addNewProduct();
                    break;
                case 4:
                    printer.print("Delete a product");
                    productService.deleteProductByID();
                    break;
                case 5:
                    printer.print("Update a product");
                    productService.updateProductById();
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
                    warehouseService.getAllWarehouse();
                    break;
                case 2:
                    warehouseService.findWarehouseById();
                    break;
                case 3:
                    printer.print("Add a new warehouse");
                    warehouseService.addNewWarehouse();
                    break;
                case 4:
                    printer.print("Delete a warehouse");
                    warehouseService.deleteWarehouseByID();
                    break;
                case 5:
                    printer.print("Update a warehouse");
                    warehouseService.updateWarehouseById();
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
