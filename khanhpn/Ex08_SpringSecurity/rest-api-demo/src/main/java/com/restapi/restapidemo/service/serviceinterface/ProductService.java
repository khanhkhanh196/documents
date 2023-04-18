package com.restapi.restapidemo.service.serviceinterface;

import com.restapi.restapidemo.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct(int position, int pageSize);

    public Product getProduct(int theId);

    public void saveProduct(Product product);

    public void deleteProduct(int theId);
}
