package com.dotseven.poc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list() {
        return userService.getUsers();
    }


    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("login")
    public String login(@RequestBody User user) {
        return userService.login(user) ? "{\"st\":true}" : "{\"st\":false}";
    }

}
