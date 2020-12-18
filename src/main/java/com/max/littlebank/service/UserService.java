package com.max.littlebank.service;

import com.max.littlebank.models.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);

    public void updateUser(User user);

    User findById(long id);

    User findByPhone(String numberPhone);

    User findByFullname(String username);

    User findByEmail(String email);

    void deleteById(long id);

    List<User> findAll();

}

