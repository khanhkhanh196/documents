package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Category;
import com.jdbc.demo.model.mapper.CategoryMapper;
import com.jdbc.demo.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class CategoryImpl implements CategoryDAO{

    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public List<Category> getAllCategory() {
        jdbcCall = new SimpleJdbcCall(dataSource);
        List<Category> categoryList;
        jdbcCall.withProcedureName("get_all_category")
                .returningResultSet("categoryList", new CategoryMapper());
        Map<String,Object> out = jdbcCall.execute();
        categoryList = (List<Category>) out.get("categoryList");
        return categoryList;
    }

    @Override
    public Category findCategoryByID(int id) {
        List<Category> categoryList;
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("get_category_by_id").returningResultSet("categoryList", new CategoryMapper());
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Map<String, Object> execute = jdbcCall.execute(param);
        categoryList = (ArrayList<Category>) execute.get("categoryList");
        return categoryList.get(0);
    }

    @Override
    public int updateCategoryByID(int id, String categoryCode, String categoryName, String categoryDescription, Timestamp updatedDate) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("update_product_by_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("category_code",categoryCode)
                .addValue("category_name",categoryName)
                .addValue("category_description",categoryDescription)
                .addValue("updated_date",updatedDate);
        jdbcCall.execute(params);
        return 0;
    }

    @Override
    public int addCategory(Category category) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("category");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(category);
        return simpleJdbcInsert.execute(parameters);
    }

    @Override
    public int deleteCategoryByID(int id) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("delete_category_by_id");
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        jdbcCall.execute(param);
        return 0;
    }
}
