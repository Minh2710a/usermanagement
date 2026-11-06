package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.AppException;
import com.example.demo.exception.ErrorCode;
import com.example.demo.model.Status;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImpl implements UserService {

    private final AuditLogService auditLogService;
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository,
            AuditLogService auditLogService) {
        this.userRepository = userRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    @Transactional
    public void addUser(User user) {

        if (userRepository.findByUsername(
                user.getUsername()).isPresent()) {

            throw new AppException(
                    ErrorCode.USERNAME_ALREADY_EXISTS);
        }

        if (userRepository.findByEmail(
                user.getEmail()).isPresent()) {

            throw new AppException(
                    ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User savedUser = userRepository.save(user);

        auditLogService.record(
                "USER_CREATED",
                "USER",
                savedUser.getId().toString(),
                savedUser.getUsername());
    }

    @Override
    public User findUserByID(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public List<User> adminUsers() {
        return userRepository.findByStatus(Status.ADMIN);
    }

    @Override
    @Transactional
    public void removeUser(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userRepository.delete(user);

        auditLogService.record(
                "USER_DELETED",
                "USER",
                id.toString(),
                user.getUsername());
    }

    @Override
    public List<User> searchUsersByUsername(String keyword) {
        return userRepository.findByUsernameContainingIgnoreCase(keyword);
    }

    @Override
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getUsers2(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}