package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

// The UserRepository interface extends CrudRepository which provides methods for CRUD operations.
// The type of entity and ID that it works with, User and String respectively, are specified in the generic parameters on CrudRepository.
public interface UserRepository extends CrudRepository<User, String> {
  // Method to find a User entity by its UserID.
  User findByUserID(String UserID);
}