package com.jdbc.demo.serviceJPA;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJPA.ProductDAOJPA;
import com.jdbc.demo.modelJPA.ProductJPA;
import com.jdbc.demo.serviceJPA.serviceInterface.ProductServiceJPAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;
import java.util.*;

@Service
@EnableTransactionManagement
public class ProductServiceJPA implements ProductServiceJPAInterface {
    @Autowired
    private ProductDAOJPA productDAOJPA;
    @Autowired
    private Printer printer;

    @Override
    public void getAllProduct(){
        List<ProductJPA> list = new ArrayList<>();
        list = productDAOJPA.findAll();
        if(!list.isEmpty()) {
            for(ProductJPA pro : list) {
                printer.print(pro.toString());
            }
        } else {
            printer.print("There is no product");
        }

    }

    @Override
    public void getProductByID(){
        try {
            Scanner sc = new Scanner(System.in);
            Optional<ProductJPA> productById = productDAOJPA.findById(sc.nextInt());
            if(productById.isEmpty()) {
                printer.print("Product doesn't exist");
            } else {
                printer.print(productById.get().toString());
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid");
        }
    }

    @Override
    public void deleteProductID(){
        try {
            Scanner sc = new Scanner(System.in);
            productDAOJPA.deleteById(sc.nextInt());
        } catch (Exception e) {
            printer.print("Your entered is invalid");
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

            ProductJPA product = new ProductJPA(productCode, productCategory,productWareHouse
                    ,productName,productDescription,productImageUrl,productQuantity,productSold,createdDate,null);

            productDAOJPA.save(product);
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
    public void updateProductByID() {
        try {
        Scanner sc = new Scanner(System.in);
        printer.print("Enter Product ID");
        int id = sc.nextInt();
        Optional<ProductJPA> product = productDAOJPA.findById(id);
        if(product.isEmpty()) {
            printer.print("Product doesn't exist");
        } else {

                if (product != null) {
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

                    ProductJPA readyToUpdate = product.get();
                    readyToUpdate.setProductCode(productCode);
                    readyToUpdate.setProductName(productName);
                    readyToUpdate.setProductCategory(productCategory);
                    readyToUpdate.setProductCategory(productWareHouse);
                    readyToUpdate.setProductDescription(productDescription);
                    readyToUpdate.setProductImageUrl(productImageUrl);
                    readyToUpdate.setProductQuantity(productQuantity);
                    readyToUpdate.setProductSold(soldQuantity);
                    readyToUpdate.setUpdatedDate(updatedDate);

                    productDAOJPA.save(readyToUpdate);
                    printer.print("Update success");
                } else {
                    printer.print("There is no product with" + id + "ID");
                }
        }
        } catch (Exception e) {
            printer.printError(e.getMessage());
        }
    }
}
