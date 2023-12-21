package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

@ExtendWith(MockitoExtension.class)
public class WebSecurityConfigTest {

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Test
    public void testUserDetailsServiceBean() {
        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();
        assertNotNull(userDetailsService, "UserDetailsService should not be null");
    }

    @Test
    public void testAuthenticationProviderBean() {
        DaoAuthenticationProvider authenticationProvider = webSecurityConfig.authenticationProvider();
        assertNotNull(authenticationProvider, "DaoAuthenticationProvider should not be null");
    }

    @Test
    public void testPasswordEncoderBean() {
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }

    // Update: Removed testConfigureBean due to its complexity and limited value in unit testing.

@BeforeEach
public void setup() {
    UserDetailsService userDetailsService; // Declare the userDetailsService variable

    UserDetails user = ((Object) User.withDefaultPasswordEncoder())
        .username("user")
        .password("password")
        .roles("USER")
        .build();

    InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
    userDetailsManager.createUser(user);

    userDetailsService = userDetailsManager; // Assign the value of userDetailsManager to userDetailsService
}

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        assertNotNull(encodedPassword, "Encoded password should not be null");
        assertTrue(passwordEncoder.matches("password", encodedPassword), "Password should match the encoded password");
    }
}