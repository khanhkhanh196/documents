package com.restapi.restapidemo.service.serviceinterface;


import com.restapi.restapidemo.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();

    public void saveCategory(Category category);

    public void deleteCategory(int theId);

    public Category getCategoryById(int theId);
}
