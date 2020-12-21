package com.max.littlebank.controller;

import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.User;
import com.max.littlebank.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUserById(@PathVariable long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/phone/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUserByPhone(@PathVariable String number) {
        User user = userService.findByPhone(number);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUserByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/fullname/{fullname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> showUserByFullname(@PathVariable String fullname) {
        User user = userService.findByFullname(fullname);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
        User userNew = userService.saveUser(user);
        return new ResponseEntity<>(userNew, HttpStatus.CREATED);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        User userNew = userService.updateUser(user);
        return new ResponseEntity<>(userNew, HttpStatus.ACCEPTED);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("User with id = " + id + " was deleted",
                HttpStatus.NO_CONTENT);
    }
}