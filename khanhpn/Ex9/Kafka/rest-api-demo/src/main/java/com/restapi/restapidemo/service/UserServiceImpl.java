package com.restapi.restapidemo.service;

import com.restapi.restapidemo.dao.UserImpl;
import com.restapi.restapidemo.entity.User;
import com.restapi.restapidemo.service.serviceinterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserImpl userImpl;

    @Override
    public User findUser(String name) {
       return userImpl.findUserByName(name);
    }

    @Override
    public User findUserByID(int userId) {
        return userImpl.findUserByID(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userImpl.findUserByName(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return user;
    }
}
