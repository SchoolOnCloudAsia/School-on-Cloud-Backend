package com.example.accessingdatamysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This means that this class is a Controller
@RequestMapping(path="/demo") // This means URL's start with /demo (after Application path)
public class MainController {
  @Autowired // This means to get the bean called userRepository
  private UserRepository userRepository;

  @PostMapping(path="/add") // Map ONLY POST Requests
  public @ResponseBody String addNewUser (@RequestParam String UserID, @RequestParam float V, @RequestParam float A, @RequestParam float K, @RequestParam String Password) {
    User n = new User();
    n.setUserID(UserID);
    n.setV(V);
    n.setA(A);
    n.setK(K);
    n.setPassword(Password);
    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/{UserID}")
  public @ResponseBody User getUser(@PathVariable String UserID) {
    return userRepository.findByUserID(UserID);
  }

  @DeleteMapping(path="/{UserID}")
  public @ResponseBody String deleteUser(@PathVariable String UserID) {
    User user = userRepository.findByUserID(UserID);
    if (user != null) {
      userRepository.delete(user);
      return "Deleted";
    } else {
      return "User not found";
    }
  }

  @GetMapping(path="/all")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}