package com.max.littlebank.controller;

import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.User;
import com.max.littlebank.service.AccountService;
import com.max.littlebank.service.AccountServiceImpl;
import com.max.littlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serov Maxim
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> showAllUsers() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User showUserById(@PathVariable long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with id = " + id + " in DataBase");
        }
        return userService.findById(id);
    }

    @GetMapping("/phone/{number}")
    public User showUserByPhone(@PathVariable String number) {
        User user = userService.findByPhone(number);
        if (user == null) {
            throw new NoSuchUserException("There is no user with phone = " + number + " in DataBase");
        }
        return userService.findByPhone(number);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        userService.saveUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable long id) {
        User user = userService.findById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with id = " + id + " in DataBase");
        }
        userService.deleteUser(id);
        return "User with id = " + id + " was deleted";
    }
}