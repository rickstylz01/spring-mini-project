package com.example.library.controller;

import com.example.library.model.User;
import com.example.library.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users") // http://localhost:9096/auth/users/
public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) { this.userService = userService; }

    @PostMapping(path = "/register/") // http://localhost:9096/auth/users/register/
    public User createUser(@RequestBody User userObject) {
        return userService.createUser(userObject);
    }
}
