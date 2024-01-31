package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SecurityConfigTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SecurityConfig securityConfig;

    @Test
    public void testUserDetailsServiceBean() {
        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        assertNotNull(userDetailsService, "UserDetailsService should not be null");
    }

    @Test
    public void testPasswordEncoderBean() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        assertNotNull(passwordEncoder, "PasswordEncoder should not be null");
    }

    @Test
    public void testUserDetailsService() {
        User testUser = new User();
        testUser.setUserID("user");
        testUser.setPassword("password");

        when(userRepository.findByUserID("user")).thenReturn(testUser);

        UserDetailsService userDetailsService = securityConfig.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals("user", userDetails.getUsername(), "Username should be 'user'");
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")), "User should have 'ROLE_USER' authority");
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = securityConfig.passwordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        assertNotNull(encodedPassword, "Encoded password should not be null");
        assertTrue(passwordEncoder.matches("password", encodedPassword), "Password should match the encoded password");
    }
}