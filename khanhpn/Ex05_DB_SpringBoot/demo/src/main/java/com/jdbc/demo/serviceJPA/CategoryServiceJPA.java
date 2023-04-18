package com.jdbc.demo.serviceJPA;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJPA.CategoryDAOJPA;
import com.jdbc.demo.modelJPA.CategoryJPA;
import com.jdbc.demo.serviceJPA.serviceInterface.CategoryServiceJPAInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Timestamp;
import java.util.*;

@Service
@EnableTransactionManagement
public class CategoryServiceJPA implements CategoryServiceJPAInterface {
    @Autowired
    private CategoryDAOJPA categoryDAOJPA;
    @Autowired
    private Printer printer;

    @Override
    public void getAllCategory(){
        List<CategoryJPA> list = new ArrayList<>();
        list = categoryDAOJPA.findAll();
        if(!list.isEmpty()) {
            for(CategoryJPA cate : list) {
                printer.print(cate.toString());
            }
        } else {
            printer.print("There is no Category");
        }
    }

    @Override
    public void getCategoryByID(){
        try {
            Scanner sc = new Scanner(System.in);
            Optional<CategoryJPA> categoryById = categoryDAOJPA.findById(sc.nextInt());
            if(categoryById.isEmpty()) {
                printer.print("Warehouse doesn't exist");
            } else {
                printer.print(categoryById.get().toString());
            }
            sc.close();
        } catch (Exception e) {
            printer.print("Your enter is invalid");
        }
    }

    @Override
    public void deleteCategoryByID(){
        try {
            Scanner sc = new Scanner(System.in);
            categoryDAOJPA.deleteById(sc.nextInt());
            sc.close();
        } catch (Exception e) {
            printer.print("Your entered is invalid");
        }
    }

    @Override
    public void addNewCategory(){
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter category code:  ");
            String cateCode = sc.nextLine();
            printer.print("Enter category name: ");
            String cateName = sc.nextLine();
            printer.print("Enter category description: ");
            String description = sc.nextLine();

            Date date = new Date();
            Timestamp time = new Timestamp(date.getTime());

            CategoryJPA cate = new CategoryJPA(cateCode,cateName,description,time,null);
            categoryDAOJPA.save(cate);
        } catch (Exception e){
            String error = "org.hibernate.exception.ConstraintViolationException";
            if(e.getCause().toString().contains(error)){
                printer.print("The warehouse code already existed, please enter another one");
            };
        }

    }

    @Override
    public void updateCategoryByID() {
        Scanner sc = new Scanner(System.in);
        printer.print("Enter Category ID");
        int id = sc.nextInt();
        Optional<CategoryJPA> categoryByID = categoryDAOJPA.findById(id);
        if(categoryByID.isEmpty()) {
            printer.print("Warehouse doesnt exist");
        } else {
            printer.print("Update warehouse");
            printer.print("Enter warehouse code:");
            sc.nextLine();
            String warehouseCode = sc.nextLine();
            printer.print("Enter category name: ");
            String categoryName = sc.nextLine();
            printer.print("Enter category description: ");
            String description = sc.nextLine();

            Date date = new Date();
            long time = date.getTime();
            Timestamp timestamp = new Timestamp(time);

            CategoryJPA category = categoryByID.get();

            category.setCategoryCode(warehouseCode);
            category.setCategoryName(categoryName);
            category.setCategoryDescription(description);
            category.setUpdatedDate(timestamp);

            categoryDAOJPA.save(category);
            printer.print("Update success");
        }
    }
}
