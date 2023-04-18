package com.jdbc.demo.model.mapper;


import com.jdbc.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductCode(rs.getString("product_code"));
        product.setProductCategory(rs.getInt("category"));
        product.setProductWareHouse(rs.getInt("warehouse"));
        product.setProductName(rs.getString("product_name"));
        product.setProductDescription(rs.getString("product_description"));
        product.setProductImageUrl(rs.getString("image_url"));
        product.setProductQuantity(rs.getInt("quantity"));
        product.setProductSold(rs.getInt("sold_quantity"));
        product.setCreatedDate(rs.getTimestamp("created_date"));
        product.setUpdatedDate(rs.getTimestamp("updated_date"));

        return product;
    }
}
