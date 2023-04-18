package com.jdbc.demo.serviceJdbcTemplate;
import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcTemplate.ProductDAO;
import com.jdbc.demo.model.Product;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.ProductServiceTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Service
public class ProductServiceJTemplate implements ProductServiceTemplateInterface {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private Printer printer;

    @Override
    public void getAllProduct(){
        ArrayList<Product> list = (ArrayList<Product>) productDAO.getAllProduct();
        if(list.isEmpty()) {
            printer.print("There is no product");
        } else {
            for(Product product : list) {
                printer.print(product.toString());
            }
        }
    }

    @Override
    public void findProductById(){
        try{
            Scanner sc = new Scanner(System.in);
            printer.print("Enter ID");
            int id = sc.nextInt();
            printer.print(productDAO.findProductByID(id).toString());
        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }
    @Override
    public void addNewProduct(){
        String productCode = "";
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter product code");
            productCode = sc.nextLine();
            printer.print("Enter product category (number)");
            int productCategory = sc.nextInt();
            printer.print("Enter product warehouse (number)");
            int productWareHouse = sc.nextInt();
            printer.print("Enter product name");
            sc.nextLine();
            String productName = sc.nextLine();
            printer.print("Enter product description");
            String productDescription = sc.nextLine();
            printer.print("Enter product image URL");
            String productImageUrl = sc.nextLine();
            printer.print("Enter product quantity");
            int productQuantity = sc.nextInt();
            int productSold = 0;
            long date = (new Date().getTime());
            Timestamp createdDate = new Timestamp(date);

            int check = productDAO.addProduct(productCode, productName, productCategory, productWareHouse,productDescription,productImageUrl,productQuantity,productSold,createdDate);
            if(check == 1) {
                printer.print("Add success");
            } else {
                printer.print("Add failed, Product code existed");
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+productCode+"' for key 'product.product_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Product code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateProductById(){
        String productCode ="";
        try{
        Scanner sc = new Scanner(System.in);
        printer.print("Enter Product ID");
        int theID = sc.nextInt();
        if(productDAO.findProductByID(theID) == null) {
            printer.print("Product doesn't exist");
            } else {
                printer.print("Enter product code");
                sc.nextLine();
                productCode = sc.nextLine();
                printer.print("Enter product category (number)");
                int productCategory = sc.nextInt();
                printer.print("Enter product warehouse (number)");
                int productWareHouse = sc.nextInt();
                printer.print("Enter product name");
                sc.nextLine();
                String productName = sc.nextLine();
                printer.print("Enter product description");
                String productDescription = sc.nextLine();
                printer.print("Enter product image URL");
                String productImageUrl = sc.nextLine();
                printer.print("Enter product quantity");
                int productQuantity = sc.nextInt();
                printer.print("Enter sold quantity");
                int soldQuantity = sc.nextInt();
                long date = (new Date().getTime());
                Timestamp updatedDate = new Timestamp(date);
                int check = productDAO.updateProductByID(theID, productCode, productName, productCategory, productWareHouse,
                        productDescription,productImageUrl,productQuantity,soldQuantity,updatedDate);
                if(check == 1) {
                    printer.print("Update success");
                } else {
                    printer.print("Update failed, Product code duplicated");
                }
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+productCode+"' for key 'product.product_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Product code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void deleteProductByID() {
        try{
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            int check = productDAO.deleteProductByID(index);
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
