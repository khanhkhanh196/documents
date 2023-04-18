package com.restapi.restapidemo.dao.daointerface;

import com.restapi.restapidemo.entity.Product;

import java.util.List;

public interface ProductDAO {
    public List<Product> getAllProduct();

    public Product getProduct(int theId);

    public void saveProduct(Product product);

    public void deleteProduct(int theId);
}
