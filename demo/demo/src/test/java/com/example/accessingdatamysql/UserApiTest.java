package com.example.accessingdatamysql;

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

    @Test
    @Order(1)
    public void testUserCanCreateAccount() {
        TestRestTemplate restTemplateWithAuth = restTemplate.withBasicAuth("user", "password");
        ResponseEntity<String> response = restTemplateWithAuth.postForEntity("/demo/add", new User("testUser", "password"), String.class);
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
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", new User("testUser", "password"), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(4)
    public void testUserCanSeeAccountDetails() {
        ResponseEntity<User> response = restTemplate.getForEntity("/demo/account", User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        User user = response.getBody();
        assertThat(user).isNotNull();
        if (user != null) {
            assertThat(user.getUsername()).isEqualTo("testUser");
        }
    }

    @Test
    @Order(5)
    public void testUserCanDeleteAccount() {
        restTemplate.delete("/demo/delete");
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/account", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(6)
    public void testUserCannotSeeDeletedAccount() {
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/account", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @Order(7)
    public void testUserCannotLoginToDeletedAccount() {
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", new User("testUser", "password"), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}