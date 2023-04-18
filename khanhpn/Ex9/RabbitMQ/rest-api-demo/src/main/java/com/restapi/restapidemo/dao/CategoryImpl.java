package com.restapi.restapidemo.dao;

import com.restapi.restapidemo.dao.daointerface.CategoryDAO;
import com.restapi.restapidemo.entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryImpl implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getAllCategory() {
        Session session = sessionFactory.getCurrentSession();
        Query<Category> query = session.createQuery("from Category",Category.class);
        List<Category> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Category getCategory(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Category category = session.get(Category.class,theId);
        return category;
    }

    @Override
    public void saveCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(category);
    }

    @Override
    public void deleteCategory(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Category where id=:categoryId");
        query.setParameter("categoryId",theId);
        query.executeUpdate();
    }
}
