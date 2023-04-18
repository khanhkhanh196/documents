package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
    @Autowired
    DataSource dataSource;

    String sql = "select * from user where username=";

    public List<User> getUserByName(String name){
        List<User> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql + "'"+ name + "'");
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String username= resultSet.getString(2);
                String password = resultSet.getString(3);
                User user = new User();
                user.setId(id);
                user.setUsername(username);
                user.setPassword(password);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

}
