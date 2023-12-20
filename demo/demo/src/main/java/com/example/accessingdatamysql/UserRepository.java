package com.example.accessingdatamysql;

import java.util.Optional; // Import the correct Optional class
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByName(String name); // Method to find a user by name

  Optional<User> findById(Long id); // Fix the Optional type

}