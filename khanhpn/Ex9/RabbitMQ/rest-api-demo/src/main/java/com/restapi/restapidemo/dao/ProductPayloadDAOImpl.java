package com.restapi.restapidemo.dao;

import com.restapi.restapidemo.dao.daointerface.ProductPayloadDAO;
import com.restapi.restapidemo.entity.ProductPayload;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductPayloadDAOImpl implements ProductPayloadDAO {
    @Autowired
    private SessionFactory factory;
    @Override
    public void save(ProductPayload productPayload) {
        Session session = factory.getCurrentSession();
        session.saveOrUpdate(productPayload);
    }
}
