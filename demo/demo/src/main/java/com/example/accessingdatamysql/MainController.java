package com.example.accessingdatamysql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // Marks this class as a web controller
@RequestMapping(path="/demo") // Maps HTTP requests to "/demo" onto methods in this controller
public class MainController {
  private UserRepository userRepository;

  public MainController(UserRepository userRepository) {
    this.userRepository = userRepository; // UserRepository is injected as a dependency
  }

  @PostMapping(path="/add") // Maps HTTP POST requests to "/demo/add" to this method
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n); // Saves the user to the database
    return "Saved"; // Returns a confirmation message
  }

  @GetMapping(path="/all") // Maps HTTP GET requests to "/demo/all" to this method
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll(); // Retrieves all users from the database
  }

  @DeleteMapping(path="/{id}") // Maps HTTP DELETE requests to "/demo/{id}" to this method
  public @ResponseBody String deleteUser(@PathVariable Integer id) {
    userRepository.deleteById(id); // Deletes the user with the provided ID from the database
    return "Deleted"; // Returns a confirmation message
  }
}