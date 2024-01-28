package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SecurityConfigTest {
    private UserRepository userRepository;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;
    private SecurityConfig securityConfig;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        userDetailsService = Mockito.mock(UserDetailsService.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        securityConfig = new SecurityConfig(userRepository, userDetailsService, passwordEncoder);
    }

    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        // Implementation of authenticationManager
        return null; // Replace null with the actual implementation
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }
}
