package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Product;
import com.jdbc.demo.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
// Tầng truy cập database
@Repository
public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private DataSource dataSource;

    private SimpleJdbcCall jdbcCall;

    private SimpleJdbcInsert simpleJdbcInsert;

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList = new ArrayList<>();
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("get_all_product")
                .returningResultSet("productList", new ProductMapper());
        Map<String,Object> out = jdbcCall.execute();
        productList = (List<Product>) out.get("productList");
        return productList;
    }

    @Override
    public Product findProductByID(int id) {
        List<Product> productList = new ArrayList<>();
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("get_product_by_id").returningResultSet("productList", new ProductMapper());
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        Map<String, Object> execute = jdbcCall.execute(param);
        productList = (ArrayList<Product>) execute.get("productList");
        return productList.get(0);
    }

    @Override
    public int updateProductByID(int id, String productCode, int productWareHouse,int productCategory ,String productName,
                                 String productDescription, String productImageUrl, int productQuantity,
                                 int productSold, Timestamp updatedDate) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("update_product_by_id");
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("id",id)
                .addValue("product_code",productCode)
                .addValue("category",productCategory)
                .addValue("warehouse",productWareHouse)
                .addValue("product_name",productName)
                .addValue("product_description",productDescription)
                .addValue("image_url",productImageUrl)
                .addValue("quantity",productQuantity)
                .addValue("sold_quantity",productSold)
                .addValue("created_date",updatedDate);
        jdbcCall.execute(params);
        return 0;
    }

    @Override
    public int addProduct(Product product) {
        simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("product");
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(product);
        return simpleJdbcInsert.execute(parameters);
    }

    @Override
    public int deleteProductByID(int id) {
        jdbcCall = new SimpleJdbcCall(dataSource);
        jdbcCall.withProcedureName("delete_product_by_id");
        SqlParameterSource param = new MapSqlParameterSource().addValue("id",id);
        jdbcCall.execute(param);
        return 0;
    }

}
