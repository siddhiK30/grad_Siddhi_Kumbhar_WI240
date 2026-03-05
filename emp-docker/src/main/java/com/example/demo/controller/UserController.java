package com.example.demo.controller;



import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.User;
import com.example.demo.repo.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    // Add user
    @PostMapping
    public User addUser(@RequestBody User user) {s
        return repo.save(user);
    }

    // Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return repo.findAll();
    }
}