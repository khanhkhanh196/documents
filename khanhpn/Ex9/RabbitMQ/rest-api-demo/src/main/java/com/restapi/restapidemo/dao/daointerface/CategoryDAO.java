package com.restapi.restapidemo.dao.daointerface;

import com.restapi.restapidemo.entity.Category;

import java.util.List;

public interface CategoryDAO {
    public List<Category> getAllCategory();

    public Category getCategory(int theId);

    public void saveCategory(Category category);

    public void deleteCategory(int theId);
}
