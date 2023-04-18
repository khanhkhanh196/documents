package com.restapi.restapidemo.dao.daointerface;
import com.restapi.restapidemo.entity.Category;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryJpaDAO extends JpaRepository<Category, Integer>{
    Page<Category> findAll(Pageable pageable);
}
