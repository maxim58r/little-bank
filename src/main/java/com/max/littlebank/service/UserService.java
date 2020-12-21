package com.max.littlebank.service;

import com.max.littlebank.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    User updateUser(User user);

    User findById(long id);

    User findByPhone(String numberPhone);

    User findByFullname(String username);

    User findByEmail(String email);

    void deleteById(long id);

    List<User> findAll();

}

