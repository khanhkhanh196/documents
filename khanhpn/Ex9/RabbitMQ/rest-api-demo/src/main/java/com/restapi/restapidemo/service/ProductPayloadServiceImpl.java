package com.restapi.restapidemo.service;

import com.restapi.restapidemo.dao.daointerface.ProductPayloadDAO;
import com.restapi.restapidemo.entity.ProductPayload;
import com.restapi.restapidemo.service.serviceinterface.ProductPayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductPayloadServiceImpl implements ProductPayloadService {
    @Autowired
    private ProductPayloadDAO dao;

    @Transactional
    @Override
    public void save(ProductPayload productPayload) {
        dao.save(productPayload);
    }
}
