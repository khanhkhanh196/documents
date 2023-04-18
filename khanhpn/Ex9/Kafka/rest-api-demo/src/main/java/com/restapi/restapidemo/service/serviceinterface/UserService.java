package com.restapi.restapidemo.service.serviceinterface;

import com.restapi.restapidemo.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findUser(String name);

    public User findUserByID(int userId);
}
