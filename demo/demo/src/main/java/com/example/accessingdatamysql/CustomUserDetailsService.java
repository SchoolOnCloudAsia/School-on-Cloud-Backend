package com.example.accessingdatamysql;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

  private UserRepository userRepository;

  public CustomUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    com.example.accessingdatamysql.User user = userRepository.findByUserID(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found");
    }
    return User.withUsername(user.getUserID())
      .password(user.getPassword())
      .roles("USER")
      .build();
  }
}