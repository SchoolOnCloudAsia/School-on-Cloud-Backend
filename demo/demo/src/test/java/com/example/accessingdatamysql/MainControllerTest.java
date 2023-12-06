package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
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
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        // Mocking the save method of userRepository
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        String response1 = mainController.addNewUser(user1.getName(), user1.getEmail());
        String response2 = mainController.addNewUser(user2.getName(), user2.getEmail());

        // Asserting that the responses are as expected
        assertEquals("Saved", response1);
        assertEquals("Saved", response2);
    }

    // Test case for getAllUsers method
    @Test
    public void testGetUsers() {
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);

        // Mocking the findAll method of userRepository
        when(userRepository.findAll()).thenReturn(users);

        Iterable<User> responseUsers = mainController.getAllUsers();

        // Asserting that the number of users retrieved is as expected
        assertEquals(users.size(), ((List<User>) responseUsers).size());
    }

    // Test case for deleteUser method
    @Test
    public void testDeleteUser() {
        Integer id = 1;

        // Mocking the deleteById method of userRepository
        doNothing().when(userRepository).deleteById(id);

        String response = mainController.deleteUser(id);

        // Asserting that the response is as expected
        assertEquals("Deleted", response);
    }
}