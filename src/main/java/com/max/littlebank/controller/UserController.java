package com.max.littlebank.controller;

import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.UserIncorrectDataEntryException;
import com.max.littlebank.models.User;
import com.max.littlebank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return userService.findById(id);
    }

    @GetMapping("/phone/{number}")
    public User showUserByPhone(@PathVariable String number) {
        return  userService.findByPhone(number);
    }

    @GetMapping("/email/{email}")
    public User showUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/fullname/{fullname}")
    public User showUserByFullname(@PathVariable String fullname) {
        return userService.findByFullname(fullname);
    }

    @PostMapping
    ResponseEntity<String> saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User is valid");
    }

    @PutMapping
    ResponseEntity<String> updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
        return ResponseEntity.ok("User is valid");
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable long id) {
        userService.deleteById(id);
        return "User with id = " + id + " was deleted";
    }
}