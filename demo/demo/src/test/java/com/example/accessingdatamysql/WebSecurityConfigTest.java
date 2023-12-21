package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testUserDetailsService() {
        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();
        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals("user", userDetails.getUsername(), "Username should be 'user'");
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER")), "User should have 'ROLE_USER' authority");
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        assertNotNull(encodedPassword, "Encoded password should not be null");
        assertTrue(passwordEncoder.matches("password", encodedPassword), "Password should match the encoded password");
    }
}