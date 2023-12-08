package com.example.accessingdatamysql;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {
  private UserRepository userRepository;

  public MainController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Endpoint to add a new user
  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String userID, @RequestParam String v, @RequestParam String a, @RequestParam String k, @RequestParam String password) {
    User n = new User();
    n.setId(Integer.parseInt(userID));
    n.setVariableV(v); // Assuming the User class has a setter method for variableV
    n.setVariableA(a); // Assuming the User class has a setter method for variableA
    n.setVariableK(k); // Assuming the User class has a setter method for variableK
    n.setPassword(password); // Set password
    userRepository.save(n);
    return "Saved";
  }

  // Endpoint to get user details
  @GetMapping(path="/{userID}")
  public @ResponseBody User getUser(@PathVariable String userID) {
    return userRepository.findById(Integer.parseInt(userID)).orElse(null);
  }

  // ...

  @DeleteMapping(path="/{userID}")
  public @ResponseBody String deleteUser(@PathVariable String userID) {
    userRepository.deleteById(Integer.parseInt(userID));
    return "Deleted";
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    userRepository.findAll().forEach(users::add);
    return users;
  }
}