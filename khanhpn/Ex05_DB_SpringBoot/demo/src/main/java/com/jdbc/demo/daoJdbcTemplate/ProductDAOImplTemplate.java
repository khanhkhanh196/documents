package com.jdbc.demo.daoJdbcTemplate;

import com.jdbc.demo.model.Category;
import com.jdbc.demo.model.Product;
import com.jdbc.demo.model.mapper.CategoryMapper;
import com.jdbc.demo.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImplTemplate implements  ProductDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Product> getAllProduct() {
        String sql = "SELECT * FROM PRODUCT";
        List<Product> productList = new ArrayList<Product>();
        productList = jdbcTemplate.query(sql, new ProductMapper());

        return productList;
    }

    @Override
    public Product findProductByID(int id) {
        Product product = new Product();
        String sql = "select * from product where product_id=?";
        product = jdbcTemplate.queryForObject(sql,new Object[]{id}, new ProductMapper());

        return product;
    }

    @Override
    public int updateProductByID(int id,String productCode,String productName, int category, int warehouse, String descript, String url, int quantity, int sold, Timestamp updatedDate) {
        String sql = "update product set " +
                "product_code=?"+"," +
                "category=?"+"," +
                "warehouse=?"+"," +
                "product_name=?"+"," +
                "product_description=?"+","+
                "quantity=?" +
                "sold_quantity=?" +
                "image_url=?" +
                "updated_date=?" +
                "where product_id=?";
        return jdbcTemplate.update(sql,new Object[]{productCode,category,warehouse,productName,descript, url,quantity, sold,updatedDate,id});
    }

    @Override
    public int addProduct(String productCode,String productName, int category, int warehouse,String descript, String url, int quantity, int sold, Timestamp createDate) {
        String sql = "insert into `product` (`product_code`,`category`,`warehouse`,`product_name`,`product_description`,`image_url`,`quantity`,`sold_quantity`,`created_date`,`updated_date`) values\n" +
                "(?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,new Object[]{productCode,category,warehouse,productName,descript, url,quantity, sold, createDate,null});
    }

    @Override
    public int deleteProductByID(int id) {
        String sql = "delete from product where product_id=?";
        return jdbcTemplate.update(sql,new Object[]{id});
    }
}
