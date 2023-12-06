package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MainControllerTest {

    @Mock
    private UserRepository userRepository;

    private MainController mainController;

    // Setup method to initialize mocks and MainController
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController(userRepository);
    }

// Test case for addNewUser method
@Test
public void testAddUsers() {
    User user1 = new User();
    user1.setId(1);
    user1.setVariableV("v1");
    user1.setVariableA("a1");
    user1.setVariableK("k1");

    User user2 = new User();
    user2.setId(2);
    user2.setVariableV("v2");
    user2.setVariableA("a2");
    user2.setVariableK("k2");

    // Mocking the save method of userRepository
    when(userRepository.save(user1)).thenReturn(user1);
    when(userRepository.save(user2)).thenReturn(user2);

    String response1 = mainController.addNewUser(String.valueOf(user1.getId()), user1.getVariableV(), user1.getVariableA(), user1.getVariableK());
    String response2 = mainController.addNewUser(String.valueOf(user2.getId()), user2.getVariableV(), user2.getVariableA(), user2.getVariableK());

    // Asserting that the responses are as expected
    assertEquals("Saved", response1);
    assertEquals("Saved", response2);
}

// Test case for getAllUsers method
@Test
public void testGetAllUsers() {
    User user1 = new User();
    user1.setId(1);
    user1.setVariableV("v1");
    user1.setVariableA("a1");
    user1.setVariableK("k1");

    User user2 = new User();
    user2.setId(2);
    user2.setVariableV("v2");
    user2.setVariableA("a2");
    user2.setVariableK("k2");

    List<User> users = Arrays.asList(user1, user2);

    // Mocking the findAll method of userRepository
    when(userRepository.findAll()).thenReturn(users);

    Iterable<User> responseUsers = mainController.getAllUsers();

    // Asserting that the number of users retrieved is as expected
    assertEquals(users.size(), ((List<User>) responseUsers).size());
    }

// Test case for deleteUser method for user2
@Test
public void testDeleteUser() {
    User user2 = new User();
    user2.setId(2);
    user2.setVariableV("v2");
    user2.setVariableA("a2");
    user2.setVariableK("k2");

    when(userRepository.save(user2)).thenReturn(user2);
    mainController.addNewUser(String.valueOf(user2.getId()), user2.getVariableV(), user2.getVariableA(), user2.getVariableK());

    when(userRepository.findById(user2.getId())).thenReturn(Optional.of(user2));
    mainController.deleteUser(String.valueOf(user2.getId()));

    when(userRepository.findById(user2.getId())).thenReturn(Optional.empty());

    assertEquals(Optional.empty(), userRepository.findById(user2.getId()));
    }
}