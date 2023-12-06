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

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
        mainController = new MainController(userRepository); // Inject mock
    }

    @Test
    public void testAddUsers() {
        // Arrange
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        // Act
        String response1 = mainController.addNewUser(user1.getName(), user1.getEmail());
        String response2 = mainController.addNewUser(user2.getName(), user2.getEmail());

        // Assert
        assertEquals("Saved", response1);
        assertEquals("Saved", response2);
    }

    @Test
    public void testGetUsers() {
        // Arrange
        User user1 = new User();
        user1.setName("User1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setName("User2");
        user2.setEmail("user2@example.com");

        List<User> users = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(users); // Mock behavior

        // Act
        Iterable<User> responseUsers = mainController.getAllUsers();

        // Assert
        assertEquals(users.size(), ((List<User>) responseUsers).size());
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Integer id = 1;
        doNothing().when(userRepository).deleteById(id); // Mock behavior

        // Act
        String response = mainController.deleteUser(id);

        // Assert
        assertEquals("Deleted", response);
    }
}