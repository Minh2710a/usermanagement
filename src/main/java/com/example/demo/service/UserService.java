package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void addUser(User user);

    User findUserByID(Integer id);

    User findUserByEmail(String email);

    List<User> adminUsers();

    void removeUser(Integer id);

    List<User> searchUsersByUsername(String keyword);
    
    Page<User> getUsers(Pageable pageable);
}