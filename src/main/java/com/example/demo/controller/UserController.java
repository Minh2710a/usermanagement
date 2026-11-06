package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user_create')")
    public void addUser(@Valid @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user_view')")
    public User findUserByID(@PathVariable Integer id) {
        return userService.findUserByID(id);
    }

    @GetMapping("/email/{email}")
    @PreAuthorize("hasAuthority('user_view')")
    public User findUserByEmail(@PathVariable String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('user_view')")
    public List<User> getAdminUsers() {
        return userService.adminUsers();
    }

    @GetMapping("/search")
    @PreAuthorize("hasAuthority('user_view')")
    public List<User> searchUsers(@RequestParam String keyword) {
        return userService.searchUsersByUsername(keyword);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user_view')")
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user_delete')")
    public void removeUser(@PathVariable Integer id) {
        userService.removeUser(id);
    }
}