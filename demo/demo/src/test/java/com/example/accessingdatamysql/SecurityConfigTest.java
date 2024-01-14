package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

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
        securityConfig = new SecurityConfig(userRepository);
    }

    @Test
    public void testAuthenticationManager() {
        when(userDetailsService.loadUserByUsername("user")).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn("encodedPassword");

        AuthenticationManager authenticationManager = securityConfig.authenticationManager(userDetailsService, passwordEncoder);

        assertNotNull(authenticationManager, "AuthenticationManager should not be null");
    }

    @Test
    public void testUserDetailsService() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();

        assertNotNull(userDetailsService, "UserDetailsService should not be null");
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();

        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }
}