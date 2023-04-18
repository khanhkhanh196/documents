package com.jdbc.demo.model.mapper;


import com.jdbc.demo.model.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {

    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        Category category = new Category();

        category.setCategoryID(rs.getInt("category_id"));
        category.setCategoryCode(rs.getString("category_code"));
        category.setCategoryName(rs.getString("category_name"));
        category.setCategoryDescription(rs.getString("category_description"));
        category.setCreatedDate(rs.getTimestamp("created_date"));
        category.setUpdatedDate(rs.getTimestamp("updated_date"));

        return category;
    }
}
