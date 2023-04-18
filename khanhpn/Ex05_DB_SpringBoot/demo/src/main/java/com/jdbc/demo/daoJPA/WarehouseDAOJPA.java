package com.jdbc.demo.daoJPA;

import com.jdbc.demo.modelJPA.WarehouseJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WarehouseDAOJPA extends JpaRepository<WarehouseJPA,Integer> {

}
