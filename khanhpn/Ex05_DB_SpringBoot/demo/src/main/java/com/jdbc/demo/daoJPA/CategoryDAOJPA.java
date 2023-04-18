package com.jdbc.demo.daoJPA;

import com.jdbc.demo.modelJPA.CategoryJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAOJPA extends JpaRepository<CategoryJPA, Integer> {
}
