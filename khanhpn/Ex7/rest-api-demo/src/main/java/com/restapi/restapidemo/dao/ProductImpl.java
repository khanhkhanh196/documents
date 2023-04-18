package com.restapi.restapidemo.dao;

import com.restapi.restapidemo.dao.daointerface.ProductDAO;
import com.restapi.restapidemo.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductImpl implements ProductDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getAllProduct() {
        Session session = sessionFactory.getCurrentSession();
        Query<Product> query =  session.createQuery("from Product", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    @Override
    public Product getProduct(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class,theId);
        return product;
    }

    @Override
    public void saveProduct(Product product) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(product);

    }

    @Override
    public void deleteProduct(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Product where id=:id");
        query.setParameter("id",theId);
        query.executeUpdate();
    }
}
