package com.example.demo.dao;

import com.example.demo.model.User;
import com.example.demo.model.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOJdbcTemplate {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> findCategoryByName(String name) {
        List<User> user = new ArrayList<>();
        String sql = "select * from user where username=";
       // user = jdbcTemplate.queryForObject(sql,new Object[]{name}, new UserMapper());
        user = jdbcTemplate.query(sql+ "'" + name + "'", new UserMapper());
        return user;
    }
}
