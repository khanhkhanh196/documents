package com.jdbc.demo.daoJdbcSimple;

import com.jdbc.demo.model.Product;

import java.sql.Timestamp;
import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProduct();

    public Product findProductByID(int id);

    public int updateProductByID(int id, String productCode, int productWareHouse,
                                 int productCategory,
                                 String productName,
                                 String productDescription,
                                 String productImageUrl,
                                 int productQuantity,
                                 int productSold,
                                 Timestamp updatedDate);

    public int addProduct(Product product);

    public int deleteProductByID(int id);
}
