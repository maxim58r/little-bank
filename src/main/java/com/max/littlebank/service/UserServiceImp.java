package com.max.littlebank.service;

import com.max.littlebank.repository.UserRepositoryJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepositoryJpa userRepositoryJpa;

    private final AccountService accountService;

    public UserServiceImp(UserRepositoryJpa userRepositoryJpa, AccountService accountService) {
        this.userRepositoryJpa = userRepositoryJpa;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public User saveUser(User user) {
        user.setId(0);
        try {
            findByFullname(user.getFullname());
            findByEmail(user.getEmail());
            findByPhone(user.getPhone());
        } catch (NoSuchUserException e) {
            userRepositoryJpa.save(user);
            return user;
        }
        throw new NoSuchUserException("A user with the same name, email or phone number already exists");
    }

    @Transactional
    @Override
    public User updateUser(User user) {
        findById(user.getId());
        userRepositoryJpa.save(user);
        return user;
    }

    @Override
    public User findById(long id) {
        return userRepositoryJpa.findById(id)
                .orElseThrow(() -> new NoSuchUserException("User with id " + id + " doesn`t exist"));
    }

    @Override
    public User findByPhone(String numberPhone) {
        return Optional.ofNullable(userRepositoryJpa.findByPhone(numberPhone))
                .orElseThrow(() -> new NoSuchUserException("User with Phone " + numberPhone + " doesn`t exist"));
    }

    @Override
    public User findByFullname(String fullname) {
        return Optional.ofNullable(userRepositoryJpa.findByFullname(fullname.toLowerCase()))
                .orElseThrow(() -> new NoSuchUserException("User with fullname " + fullname + " doesn`t exist"));
    }

    @Override
    public User findByEmail(String email) {
        return Optional.ofNullable(userRepositoryJpa.findByEmail(email))
                .orElseThrow(() -> new NoSuchUserException("User with email " + email + " doesn`t exist"));
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        User user = findById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with id = " + id + " in DataBase");
        }
        accountService.deleteAllByOwner_Id(id);
        userRepositoryJpa.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepositoryJpa.findAll();
    }
}