package com.jdbc.demo.serviceJdbcSimple;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcSimple.CategoryDAO;
import com.jdbc.demo.model.Category;
import com.jdbc.demo.serviceJdbcSimple.servieInterface.CategoryServiceSimpleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Service
public class CategoryServiceSimple implements CategoryServiceSimpleInterface {
    @Autowired
    private CategoryDAO categoryDAO;
    @Autowired
    private Printer printer;

    @Override
    public void getAllCategory(){
        ArrayList<Category> listCategory = (ArrayList<Category>) categoryDAO.getAllCategory();
        if(listCategory.isEmpty()){
            printer.print("There is no category");
        } else {
            for(Category category : listCategory) {
                printer.print(category.toString());
            }
        }
    }

    @Override
    public void findCategoryByID(){
        try{
            Scanner sc = new Scanner(System.in);
            printer.print("Enter ID");
            int id = sc.nextInt();
            Category category = categoryDAO.findCategoryByID(id);
            if(category!= null) {
                printer.print(category.toString());
            } else{
                printer.print("There is no category with" + id + "ID");
            }

        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public  void deleteCategoryWithID(){
        try{
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            categoryDAO.deleteCategoryByID(index);
            printer.print("Delete success");
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public void addNewCategory(){
        String categoryCode = "";
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter category code");
            categoryCode = sc.nextLine();
            printer.print("Enter category name");
            sc.nextLine();
            String categoryName = sc.nextLine();
            printer.print("Enter category location");
            String location = sc.nextLine();

            long date = (new Date().getTime());
            Timestamp createdDate = new Timestamp(date);

            Category category = new Category(categoryCode, categoryName,location,createdDate,null);
            categoryDAO.addCategory(category);
        }
        catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+categoryCode+"' for key 'category.category_code'";
            printer.print(e.getMessage());
            if (e.getCause()!= null && e.getCause().toString().contains(cause)) {
                printer.print("Category code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateCategoryById(){
        try {
            Scanner sc = new Scanner(System.in);
            printer.print("Enter category id");
            int id = sc.nextInt();
            Category category = categoryDAO.findCategoryByID(id);
            if(category!= null) {
                printer.print("Enter category code");
                String categoryCode = sc.nextLine();
                printer.print("Enter category name");
                sc.nextLine();
                String categoryName = sc.nextLine();
                printer.print("Enter category description");
                String description = sc.nextLine();

                long date = (new Date().getTime());
                Timestamp updatedDate = new Timestamp(date);
                categoryDAO.updateCategoryByID
                        (id,categoryCode,categoryName,description,updatedDate);
            } else{
                printer.print("There is no category with" + id + "ID");
            }

        }catch (Exception e) {
            printer.printError(e.getMessage());
        }

    }
}
