package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {

    void addUser(User user);

    User findUserByID(Integer id);

    User findUserByEmail(String email);

    List<User> adminUsers();

    void removeUser(Integer id);

    boolean login(String username, String password);
}