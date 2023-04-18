package com.jdbc.demo.daoJdbcTemplate;


import com.jdbc.demo.model.Product;

import java.sql.Timestamp;
import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProduct();

    public Product findProductByID(int id);

    public int updateProductByID(int id, String productCode,String productName, int category, int warehouse,String descript, String url, int quantity, int sold, Timestamp updatedDat);

    public int addProduct(String productCode,String productName, int category, int warehouse,String descript, String url, int quantity,int sold, Timestamp createDate);

    public int deleteProductByID(int id);
}
