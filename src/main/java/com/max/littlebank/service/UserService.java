package com.max.littlebank.service;


import com.max.littlebank.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    User findById(long id);

    User findByPhone(String numberPhone);

    User findByUsername(String username);

    User findByEmail(String email);

    void deleteUser(long id);

    List<User> findAll();

}

