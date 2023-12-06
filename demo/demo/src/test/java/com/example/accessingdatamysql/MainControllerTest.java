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

    @Mock // This annotation is used to create a mock implementation of UserRepository
    private UserRepository userRepository;

    private MainController mainController;

    @BeforeEach // This annotation defines the method to be executed before each test
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initializes objects annotated with @Mock
        mainController = new MainController(userRepository); // Inject the mock into the MainController
    }

    @Test // This annotation indicates that the public void method to which it is attached can be run as a test case
    public void testAddUsers() {
        // Arrange
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        // Define the behavior of the mock object
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        // Act
        String response1 = mainController.addNewUser(user1.getName(), user1.getEmail());
        String response2 = mainController.addNewUser(user2.getName(), user2.getEmail());

        // Assert
        assertEquals("Saved", response1); // Check if the method returns "Saved" for user1
        assertEquals("Saved", response2); // Check if the method returns "Saved" for user2
    }

    @Test // This annotation indicates that the public void method to which it is attached can be run as a test case
    public void testGetUsers() {
        // Arrange
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users); // Define the behavior of the mock object

        // Act
        Iterable<User> responseUsers = mainController.getAllUsers(); // Call the method to be tested

        // Assert
        assertEquals(users.size(), ((List<User>) responseUsers).size()); // Check if the method returns the correct number of users
    }

    @Test // This annotation indicates that the public void method to which it is attached can be run as a test case
    public void testDeleteUser() {
        // Arrange
        Integer id = 1;
        doNothing().when(userRepository).deleteById(id); // Define the behavior of the mock object

        // Act
        String response = mainController.deleteUser(id); // Call the method to be tested

        // Assert
        assertEquals("Deleted", response); // Check if the method returns "Deleted"
    }
}