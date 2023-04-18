package com.jdbc.demo.daoJPA;

import com.jdbc.demo.modelJPA.ProductJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAOJPA extends JpaRepository<ProductJPA, Integer> {
}
