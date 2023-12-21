package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        restTemplate.postForEntity("/demo/add", new User("testUser", "password"), String.class);
        restTemplate.delete("/demo/delete");
    }

    // Remove the duplicate setup() method

    @Test
    @Order(1)
    public void testUserCannotLoginWithoutCredentials() {
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @Order(2)
    public void testUserCanLoginWithCredentials() {
        TestRestTemplate restTemplateWithAuth = restTemplate.withBasicAuth("testUser", "password");
        ResponseEntity<String> response = restTemplateWithAuth.postForEntity("/demo/login", null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(3)
    public void testUserCanSeeAccountDetails() {
        TestRestTemplate restTemplateWithAuth = restTemplate.withBasicAuth("testUser", "password");
        ResponseEntity<User> response = restTemplateWithAuth.getForEntity("/demo/account", User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        User user = response.getBody();
        assertThat(user).isNotNull();
        if (user != null) {
            assertThat(user.getUsername()).isEqualTo("testUser");
        }
    }

    @Test
    @Order(4)
    public void testUserCanDeleteAccount() {
        TestRestTemplate restTemplateWithAuth = restTemplate.withBasicAuth("testUser", "password");
        restTemplateWithAuth.delete("/demo/delete");
        ResponseEntity<String> response = restTemplateWithAuth.getForEntity("/demo/account", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Order(5)
    public void testUserCannotSeeDeletedAccount() {
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/account", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test
    @Order(6)
    public void testUserCannotLoginToDeletedAccount() {
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", new User("testUser", "password"), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}