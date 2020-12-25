package com.max.littlebank.service;

import com.max.littlebank.repository.UserRepositoryJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.UserIncorrectDataEntryException;
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
        if (findByFullname(user.getFullname()) == null ||
                findByEmail(user.getEmail()) == null ||
                findByPhone(user.getPhone()) == null) {
            return userRepositoryJpa.save(user);
        } else {
            throw new UserIncorrectDataEntryException("A user exist in DataBase");
        }
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
        User user;
        Optional<User> optionalUser = userRepositoryJpa.findById(id);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NoSuchUserException("User with " + id + " doesn`t exist");
        }
        return user;
    }

    @Override
    public User findByPhone(String numberPhone) {
        User user;
        Optional<User> optionalUser = Optional.ofNullable(userRepositoryJpa.findByPhone(numberPhone));
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NoSuchUserException("User with " + numberPhone + " doesn`t exist");
        }
        return user;
    }

    @Override
    public User findByFullname(String fullname) {
        User user;
        Optional<User> optionalUser = Optional.ofNullable(userRepositoryJpa.findByFullname(fullname));
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NoSuchUserException("User with " + fullname + " doesn`t exist");
        }
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user;
        Optional<User> optionalUser = Optional.ofNullable(userRepositoryJpa.findByEmail(email));
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        } else {
            throw new NoSuchUserException("User with " + email + " doesn`t exist");
        }
        return user;
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