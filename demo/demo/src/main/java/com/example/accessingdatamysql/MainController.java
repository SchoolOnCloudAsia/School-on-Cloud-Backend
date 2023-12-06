package com.example.accessingdatamysql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {
  private UserRepository userRepository;

  // Dependency injection of UserRepository
  public MainController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Endpoint to add a new user
  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved";
  }

  // Endpoint to get all users
  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }

  // Endpoint to delete a user by ID
  @DeleteMapping(path="/{id}")
  public @ResponseBody String deleteUser(@PathVariable Integer id) {
    userRepository.deleteById(id);
    return "Deleted";
  }
}