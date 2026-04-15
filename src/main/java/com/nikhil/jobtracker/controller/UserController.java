package com.nikhil.jobtracker.controller;

import com.nikhil.jobtracker.entity.User;
import com.nikhil.jobtracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

}
