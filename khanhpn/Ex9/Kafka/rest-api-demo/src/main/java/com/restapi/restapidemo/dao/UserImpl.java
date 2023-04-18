package com.restapi.restapidemo.dao;

import com.restapi.restapidemo.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public class UserImpl {
    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public User findUserByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where username=:userName");
        query.setParameter("userName",name);
        return (User) query.getResultList().get(0);
    }

    @Transactional
    public User findUserByID(int userId) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,userId);
        return user;
    }
}
