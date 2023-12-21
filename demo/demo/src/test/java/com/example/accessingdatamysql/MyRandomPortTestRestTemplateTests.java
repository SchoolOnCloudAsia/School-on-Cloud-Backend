package com.example.accessingdatamysql;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
class MyRandomPortTestRestTemplateTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void userCanCreateAccount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userID", "1");
        map.add("v", "value1");
        map.add("a", "value2");
        map.add("k", "value3");
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/demo/add", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(2)
    void userCannotLoginWithoutCredentials() {
        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", null, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    @Order(3)
    void userCanLoginWithCredentials() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userID", "1");
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @Order(4)
    void userCanSeeAccountDetails() {
        ResponseEntity<User> response = restTemplate.getForEntity("/demo/1", User.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        User responseBody = response.getBody();
        assertThat(responseBody).isNotNull();
        if (responseBody != null) {
            assertThat(responseBody.getId()).isEqualTo(1);
            assertThat(responseBody.getVariableV()).isEqualTo("value1");
            assertThat(responseBody.getVariableA()).isEqualTo("value2");
            assertThat(responseBody.getVariableK()).isEqualTo("value3");
        }
    }

    @Test
    @Order(5)
    void userCanDeleteAccount() {
        restTemplate.delete("/demo/1");
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Order(6)
    void userCannotSeeDeletedAccount() {
        ResponseEntity<String> response = restTemplate.getForEntity("/demo/1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @Order(7)
    void userCannotLoginToDeletedAccount() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("userID", "1");
        map.add("password", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/demo/login", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }
}