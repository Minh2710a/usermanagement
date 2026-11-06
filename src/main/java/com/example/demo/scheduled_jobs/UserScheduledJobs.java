package com.example.demo.scheduled_jobs;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserScheduledJobs {

    private final UserRepository userRepository;

    public UserScheduledJobs(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(fixedRate = 120000)
    public void printAllUsers() {

        List<User> users = userRepository.findAll();

        System.out.println("===== USER LIST =====");

        for (User user : users) {
            System.out.println(
                "ID: " + user.getId() +
                ", Keycloak ID: " + user.getKeycloakId() +
                ", Username: " + user.getUsername() +
                ", Fullname: " + user.getFullname() +
                ", Email: " + user.getEmail() +
                ", Status: " + user.getStatus()
            );
        }

        System.out.println("=====================");
    }
}