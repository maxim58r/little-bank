package com.max.littlebank.service;

import com.max.littlebank.dao.UserDaoJpa;
import com.max.littlebank.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final  UserDaoJpa userDaoJpa;

    public UserServiceImp(UserDaoJpa userDaoJpa) {
        this.userDaoJpa = userDaoJpa;
    }

    @Override
    public void saveUser(User user) {
        userDaoJpa.save(user);
    }

    @Override
    public User findById(long id) {
        User user = null;
        Optional<User> optionalUser = userDaoJpa.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    public User findByPhone(String numberPhone) {
        return userDaoJpa.findByPhone(numberPhone);
    }

    @Override
    public User findByFullname(String fullname) {
        return userDaoJpa.findByFullname(fullname);
    }

    @Override
    public User findByEmail(String email) {
        return userDaoJpa.findByEmail(email);
    }


    @Override
    public void deleteUser(long id) {
        userDaoJpa.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDaoJpa.findAll();
    }
}