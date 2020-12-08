package com.max.littlebank.service;

import com.max.littlebank.dao.Dao;
import com.max.littlebank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private Dao dao;

    @Transactional
    @Override
    public void saveUser(User user) {
        dao.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User findById(long id) {
        User user = null;
        Optional<User> optionalUser = dao.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Transactional
    @Override
    public void deleteUser(long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }
}