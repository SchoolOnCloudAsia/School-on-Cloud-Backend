package com.example.accessingdatamysql;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.MethodOrderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiE2ETest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void testUserCanCreateAccount() {
        User user = new User();
        user.setName("testUser");
        user.setEmail("testUser@example.com");
        user.setVariableV("value1");
        user.setVariableA("value2");
        user.setVariableK("value3");
        user.setPassword("password");

        ResponseEntity<User> response = restTemplate.postForEntity("/demo/add", user, User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(2)
    public void testUserCannotLoginWithoutCredentials() {
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
    
    @Test
    @Order(3)
    public void testUserCanLoginWithCredentials() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userID", "1");
        map.add("password", "password");
    
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());
    
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(4)
    public void testUserCanSeeAccountDetails() {
        ResponseEntity<User> response = restTemplate.getForEntity("/demo/1", User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        User returnedUser = response.getBody();

        // Check if returnedUser is not null before accessing its methods
        if (returnedUser != null) {
            assertThat(returnedUser.getName()).isEqualTo("testUser");
            assertThat(returnedUser.getEmail()).isEqualTo("testUser@example.com");
            assertThat(returnedUser.getVariableV()).isEqualTo("value1");
            assertThat(returnedUser.getVariableA()).isEqualTo("value2");
            assertThat(returnedUser.getVariableK()).isEqualTo("value3");
            assertThat(returnedUser.getPassword()).isEqualTo("password");
        } else {
            fail("Returned user is null");
        }
    }

    @Test
    @Order(5)
    public void testUserCanDeleteAccount() {
        restTemplate.exchange("/demo/1", HttpMethod.DELETE, null, Void.class);
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(6)
    public void testUserCannotSeeDeletedAccount() {
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(7)
    public void testUserCannotLoginToDeletedAccount() {
        // Assuming there's a /demo/login endpoint
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "testUser");
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, new HttpHeaders());

        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}