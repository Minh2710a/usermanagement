package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    public User findUserByID(@PathVariable Integer id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/email/{email}")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }
    

    @GetMapping("/admins")
    public List<User> getAdminUsers() {
        return userService.adminUsers();
    }

    @DeleteMapping("/{id}")
    public void removeUser(@PathVariable Integer id) {
        userService.removeUser(id);
    }
}