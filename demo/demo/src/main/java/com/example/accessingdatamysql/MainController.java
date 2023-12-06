package com.example.accessingdatamysql;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller // This annotation indicates that this class serves as a web controller
@RequestMapping(path="/demo") // This annotation maps HTTP requests to the path "/demo" onto methods in this controller
public class MainController {
  private UserRepository userRepository;

  // Constructor for the MainController class, UserRepository is injected as a dependency
  public MainController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping(path="/add") // This annotation ensures that HTTP POST requests to "/demo/add" are mapped to this method
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
    // @ResponseBody annotation tells a controller that the object returned is automatically serialized into JSON and passed back into the HttpResponse object
    // @RequestParam binds the value of the query string parameter 'name' and 'email' into the respective method parameters 'name' and 'email'

    User n = new User();
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n); // Save the user to the database
    return "Saved"; // Return a confirmation message
  }

  @GetMapping(path="/all") // This annotation ensures that HTTP GET requests to "/demo/all" are mapped to this method
  public @ResponseBody Iterable<User> getAllUsers() {
    // This method returns a JSON or XML with all users
    return userRepository.findAll(); // Retrieve all users from the database
  }

  @DeleteMapping(path="/{id}") // This annotation ensures that HTTP DELETE requests to "/demo/{id}" are mapped to this method
  public @ResponseBody String deleteUser(@PathVariable Integer id) {
    // @PathVariable annotation indicates that a method parameter should be bound to a URI template variable
    userRepository.deleteById(id); // Delete the user with the provided ID from the database
    return "Deleted"; // Return a confirmation message
  }
}