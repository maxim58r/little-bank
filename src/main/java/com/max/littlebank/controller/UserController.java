package com.max.littlebank.controller;

import com.max.littlebank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serov Maxim
 */
//@RestController
//@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
//
//    @Autowired
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping(value = "/")
//    public String showUsers(Model model) {
//        model.addAttribute("users", userService.findAll());
//        return "showUsers";
//    }
//
//    @GetMapping(value = "/{id}")
//    public String showUser(@PathVariable("id") long id,
//                           Model model) {
//        model.addAttribute("user", userService.findById(id));
//        return "showUser";
//    }
}
