package com.recommend.controller;

import com.recommend.bean.User;
import com.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        if (!userService.login(user)) {
            return "false";
        }

        return "ok";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {

        if (userService.register(user)) {
            return "ok";
        }
        return "false";
    }
}
