package com.restapi.restapidemo.dao;

import com.restapi.restapidemo.dao.daointerface.WarehouseDAO;
import com.restapi.restapidemo.entity.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseImpl implements WarehouseDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Warehouse> getAllWarehouse() {
        Session session = sessionFactory.getCurrentSession();
        Query<Warehouse> query =  session.createQuery("from Warehouse", Warehouse.class);
        List<Warehouse> warehouseList = query.getResultList();
        return warehouseList;
    }

    @Override
    public Warehouse getWarehouse(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Warehouse warehouse = session.get(Warehouse.class,theId);
        return warehouse;
    }

    @Override
    public void saveWarehouse(Warehouse warehouse) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(warehouse);
    }

    @Override
    public void deleteWarehouse(int theId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from Warehouse where id=:id");
        query.setParameter("id",theId);
        query.executeUpdate();
    }
}
