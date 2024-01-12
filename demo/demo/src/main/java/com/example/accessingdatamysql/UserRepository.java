package com.example.accessingdatamysql;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

public interface UserRepository extends CrudRepository<User, Integer> {

  User findByUserID(String UserID); // Method to find a user by UserID

  @NonNull
  Optional<User> findById(@NonNull Integer id); // Method to find a user by id

}