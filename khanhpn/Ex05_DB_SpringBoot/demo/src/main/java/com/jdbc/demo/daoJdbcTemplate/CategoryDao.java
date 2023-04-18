package com.jdbc.demo.daoJdbcTemplate;

import com.jdbc.demo.model.Category;


import java.sql.Timestamp;

import java.util.List;

public interface CategoryDao {
    public List<Category> getAllCategory();

    public Category findCategoryByID(int id);

    public int updateCategoryByID(int id, String categoryCode, String categoryName, String categoryDescription, Timestamp updatedDate);

    public int addCategory(String categoryCode, String categoryName, String categoryDescription, Timestamp createdDate);

    public int deleteCategoryByID(int id);
}
