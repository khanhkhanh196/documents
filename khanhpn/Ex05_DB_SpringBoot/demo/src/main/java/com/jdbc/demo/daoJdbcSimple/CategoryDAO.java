package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Category;

import java.sql.Timestamp;
import java.util.List;

public interface CategoryDAO {
    public List<Category> getAllCategory();

    public Category findCategoryByID(int id);

    public int updateCategoryByID(int id, String categoryCode, String categoryName, String categoryDescription, Timestamp updatedDate);

    public int addCategory(Category category);

    public int deleteCategoryByID(int id);
}
