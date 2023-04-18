package com.jdbc.demo.serviceJdbcTemplate;

import com.jdbc.demo.commom.Printer;
import com.jdbc.demo.daoJdbcTemplate.CategoryDao;
import com.jdbc.demo.model.Category;
import com.jdbc.demo.serviceJdbcTemplate.serviceInterface.CategoryServiceTemplateInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

@Service
public class CategoryServiceJTemplate implements CategoryServiceTemplateInterface {

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private Printer printer;

    @Override
    public void getAllCategory(){
        ArrayList<Category> list = (ArrayList<Category>) categoryDao.getAllCategory();
        if(list.isEmpty()) {
            printer.print("There is no category");
        } else {
            for(Category category : list) {
                printer.print(category.toString());
            }
        }
    }

    @Override
    public void findCategoryById(){
        try{
        Scanner sc = new Scanner(System.in);
        printer.print("Enter ID");
        int id = sc.nextInt();
        printer.print(categoryDao.findCategoryByID(id).toString());
        } catch (Exception e) {
            printer.print(e.getMessage());
            printer.print("Your enter is invalid, Please try again");
        }
    }

    @Override
    public void addNewCategory(){
        String categoryCode = "";
        try {
            Scanner scUpdate = new Scanner(System.in);
            printer.print("Enter new Category code");
            categoryCode = scUpdate.nextLine();
            printer.print("Enter new Category name");
            scUpdate.nextLine();
            String categoryName = scUpdate.nextLine();
            printer.print("Enter category description");
            String description = scUpdate.nextLine();
            long date = (new Date().getTime());
            Timestamp datetime = new Timestamp(date);
            int check = categoryDao.addCategory(categoryCode, categoryName, description, datetime);
            if(check == 1) {
                printer.print("Add success");
            } else {
                printer.print("Add failed, Category code existed");
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+categoryCode+"' for key 'category.category_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Category code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void updateCategoryById(){
        String categoryCode = "";
        try{
            Scanner scUpdate = new Scanner(System.in);
            printer.print("Enter ID");
            int theID = scUpdate.nextInt();
            printer.print("Enter new Category code");
            categoryCode = scUpdate.nextLine();
            printer.print("Enter new Category name");
            scUpdate.nextLine();
            String categoryName = scUpdate.nextLine();
            printer.print("Enter category description");
            String description = scUpdate.nextLine();
            long date = (new Date().getTime());
            Timestamp datetime = new Timestamp(date);
            int check = categoryDao.updateCategoryByID(theID, categoryCode, categoryName, description, datetime);
            if(check == 1) {
                printer.print("Update success");
            } else {
                printer.print("Update failed, Category code duplicated");
            }
        } catch (Exception e) {
            printer.print("Your enter is invalid, Please try again");
            String cause = "Duplicate entry '"+categoryCode+"' for key 'category.category_code'";
            if (e.getCause().toString().contains(cause)) {
                printer.print("Category code already existed, Enter a new one");
            }
        }
    }

    @Override
    public void deleteCategoryByID() {
        try{
        Scanner sc = new Scanner(System.in);
        int index = sc.nextInt();
        int check = categoryDao.deleteCategoryByID(index);
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
