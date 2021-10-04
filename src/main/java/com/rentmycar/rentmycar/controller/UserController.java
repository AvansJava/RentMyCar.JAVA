package com.rentmycar.rentmycar.controller;

import com.rentmycar.rentmycar.model.User;
import com.rentmycar.rentmycar.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1.0/user")
public class UserController {

    private final UserService userService;

    @Autowired

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PutMapping(path = "/{id}")
    public User updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }
}