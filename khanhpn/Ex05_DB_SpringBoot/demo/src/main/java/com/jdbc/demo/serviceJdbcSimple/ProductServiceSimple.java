package com.jdbc.demo.serviceJdbcSimple;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcSimple.ProductDAO;
import com.jdbc.demo.model.Product;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.ProductServiceSimpleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Component
public class ProductServiceSimple implements ProductServiceSimpleInterface {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private Printer printer;

    @Override
    public void getAllProduct(){
        ArrayList<Product> listProduct = (ArrayList<Product>) productDAO.getAllProduct();
        if(listProduct.isEmpty()){
            printer.print("There is no product");
        } else {
            for(Product product : listProduct) {
                printer.print(product.toString());
            }
        }
    }

    @Override
    public void findProductByID(){
        try{
            Scanner sc = new Scanner(System.in);
            printer.print("Enter ID");
            int id = sc.nextInt();
            Product product = productDAO.findProductByID(id);
            if(product!= null) {
                printer.print(product.toString());
            } else{
                printer.print("There is no product with" + id + "ID");
            }

        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public  void deleteProductWithID(){
        try{
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            productDAO.deleteProductByID(index);
            printer.print("Delete success");
        } catch (Exception e) {
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
            Product product = new Product(productCode, productCategory,productWareHouse
                    ,productName,productDescription,productImageUrl,productQuantity,productSold,createdDate,null);
            productDAO.addProduct(product);
        }
        catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+productCode+"' for key 'product.product_code'";
            printer.print(e.getMessage());
            if (e.getCause()!= null && e.getCause().toString().contains(cause)) {
                printer.print("Product code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateProductById(){
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter product id");
            int id = sc.nextInt();
            Product product = productDAO.findProductByID(id);
            if(product!= null) {
                printer.print("Enter product code");
                sc.nextLine();
                String productCode = sc.nextLine();
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
                productDAO.updateProductByID
                        (id,productCode,productCategory,productWareHouse,productName,productDescription,productImageUrl,productQuantity,soldQuantity,updatedDate);
            } else{
                printer.print("There is no product with" + id + "ID");
            }
        }catch (Exception e) {
                printer.printError(e.getMessage());
        }

    }
}
