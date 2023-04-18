package com.restapi.restapidemo.service;

import com.restapi.restapidemo.dao.daointerface.ProductDAO;
import com.restapi.restapidemo.entity.Product;
import com.restapi.restapidemo.service.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDao;

    @Transactional
    @Override
    public List<Product> getAllProduct(int position, int pageSize) {
        List<Product> productList = productDao.getAllProduct();
        int totalRecorded = (int)productList.stream().count();
        int totalPage = totalRecorded / pageSize;
        if(pageSize > totalRecorded) {
            pageSize = totalRecorded;
        }
        List<Product> limitedBy10List = productList.subList(position,pageSize);
        return limitedBy10List;
    }

    @Transactional
    @Override
    public Product getProduct(int theId) {
        return productDao.getProduct(theId);
    }

    @Transactional
    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Transactional
    @Override
    public void deleteProduct(int theId) {
        productDao.deleteProduct(theId);
    }
}
