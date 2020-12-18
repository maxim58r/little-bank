package com.max.littlebank.service;

import com.max.littlebank.dao.UserDaoJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.UserIncorrectDataEntryException;
import com.max.littlebank.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserDaoJpa userDaoJpa;

    private final AccountService accountService;

    public UserServiceImp(UserDaoJpa userDaoJpa, AccountService accountService) {
        this.userDaoJpa = userDaoJpa;
        this.accountService = accountService;
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        user.setId(0);
        if (findByFullname(user.getFullname()) == null ||
                findByEmail(user.getEmail()) == null ||
                findByPhone(user.getPhone()) == null) {
            userDaoJpa.save(user);
        } else {
            throw new UserIncorrectDataEntryException("A user exist in DataBase");
        }
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        findById(user.getId());
        userDaoJpa.save(user);
    }

    @Override
    public User findById(long id) {
        User user;
        Optional<User> optionalUser = userDaoJpa.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NoSuchUserException("User with " + id + " doesn`t exist");
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

    @Transactional
    @Override
    public void deleteById(long id) {
        User user = findById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with id = " + id + " in DataBase");
        }
        accountService.deleteAllByOwner_Id(id);
        userDaoJpa.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDaoJpa.findAll();
    }
}