package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> getUsers() {
        return users;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // Find and return a user by ID
        // You can add error handling if the user is not found
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // Create a new user and add it to the list
        users.add(user);
        return user;
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        // Find and update a user by ID
        // You can add error handling if the user is not found
        return user;
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        // Find and delete a user by ID
        // You can add error handling if the user is not found
    }
}
