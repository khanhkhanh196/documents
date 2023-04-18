package com.jdbc.demo.daoJdbcTemplate;

import com.jdbc.demo.model.Category;
import com.jdbc.demo.model.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

// Tầng truy cập database
@Repository
public class CategoryDAOImplTemplate implements  CategoryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getAllCategory() {
        String sql = "SELECT * FROM CATEGORY";
        List<Category> categoriesList = new ArrayList<Category>();
        categoriesList = jdbcTemplate.query(sql, new CategoryMapper());

        return categoriesList;
    }

    @Override
    public Category findCategoryByID(int id) {
        Category category = new Category();
        String sql = "select * from category where category_id=?";
        category = jdbcTemplate.queryForObject(sql,new Object[]{id}, new CategoryMapper());

        return category;
    }

    @Override
    public int updateCategoryByID(int id, String categoryCode, String categoryName, String categoryDescription, Timestamp updatedDate) {
        String sql = "update category set " +
                "category_code=?"+"," +
                "category_name=?"+"," +
                "category_description=?"+","+
                "updated_date=?" +
               "where category_id=?";
        return jdbcTemplate.update(sql,new Object[]{categoryCode,categoryName,categoryDescription,updatedDate,id});
    }

    @Override
    public int addCategory(String categoryCode, String categoryName, String categoryDescription, Timestamp createdDate) {
        String sql = "insert into category  (`category_code`,`category_name`,`category_description`,`created_date`) values (?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{categoryCode,categoryName,categoryDescription,createdDate});
    }

    @Override
    public int deleteCategoryByID(int id) {
        String sql = "delete from category where category_id=?";
        return jdbcTemplate.update(sql,new Object[]{id});
    }
}
