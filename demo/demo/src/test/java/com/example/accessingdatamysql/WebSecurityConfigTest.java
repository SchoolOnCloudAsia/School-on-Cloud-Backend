package com.example.accessingdatamysql;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WebSecurityConfigTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private WebSecurityConfig webSecurityConfig;

    @Test
    public void testUserDetailsService() {
        User user = new User();
        user.setName("test");
        user.setPassword("password");

        when(userRepository.findByName("test")).thenReturn(user);

        UserDetailsService userDetailsService = webSecurityConfig.userDetailsService();

        assertNotNull(userDetailsService.loadUserByUsername("test"));
    }

    @Test
    public void testAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = webSecurityConfig.authenticationProvider();

        assertNotNull(authProvider);
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = webSecurityConfig.passwordEncoder();

        assertNotNull(passwordEncoder);
        assertTrue(passwordEncoder instanceof BCryptPasswordEncoder);
    }
}