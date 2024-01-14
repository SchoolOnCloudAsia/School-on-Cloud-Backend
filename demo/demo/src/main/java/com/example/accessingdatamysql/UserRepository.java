package com.example.accessingdatamysql;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
  // No additional methods are needed here, as all the basic CRUD operations
  // are already provided by the CrudRepository interface.
}