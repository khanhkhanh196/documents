package com.restapi.restapidemo.service;

import com.restapi.restapidemo.dao.daointerface.CategoryDAO;
import com.restapi.restapidemo.entity.Category;
import com.restapi.restapidemo.service.serviceinterface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;



    @Transactional
    @Override
    public List<Category> getAllCategory() {

        return categoryDAO.getAllCategory();
    }

    @Transactional
    @Override
    public void saveCategory(Category category) {
        categoryDAO.saveCategory(category);
    }

    @Transactional
    @Override
    public void deleteCategory(int theId) {
        categoryDAO.deleteCategory(theId);
    }

    @Transactional
    @Override
    public Category getCategoryById(int theId) {
        return categoryDAO.getCategory(theId);
    }

}
