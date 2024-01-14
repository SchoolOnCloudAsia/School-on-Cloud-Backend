package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class MainControllerTest {
    private UserRepository userRepository;
    private MainController mainController;
    private User user1;
    private User user2;

    @BeforeEach
    public void setup() {
        userRepository = Mockito.mock(UserRepository.class);
        mainController = new MainController(userRepository);

        user1 = new User();
        user1.setDateTime(new Date());
        user1.setUserID("user1");
        user1.setPassword("password1");
        user1.setA(1.0f);
        user1.setK(1.0f);
        user1.setV(1.0f);

        user2 = new User();
        user2.setDateTime(new Date());
        user2.setUserID("user2");
        user2.setPassword("password2");
        user2.setA(2.0f);
        user2.setK(2.0f);
        user2.setV(2.0f);
    }

    @Test
    public void testAddNewUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        String response1 = mainController.addNewUser(user1.getUserID(), user1.getPassword(), user1.getA(), user1.getK(), user1.getV());
        String response2 = mainController.addNewUser(user2.getUserID(), user2.getPassword(), user2.getA(), user2.getK(), user2.getV());

        assertEquals("Saved", response1);
        assertEquals("Saved", response2);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        Iterable<User> users = mainController.getAllUsers();

        assertEquals(user1, users.iterator().next());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1);
        doNothing().when(userRepository).deleteById(2);

        String response1 = mainController.deleteUser(1);
        String response2 = mainController.deleteUser(2);

        assertEquals("Deleted", response1);
        assertEquals("Deleted", response2);
    }

    @Test
    public void testDeleteUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        String response = mainController.deleteUser(1);

        assertEquals("User with id 1 does not exist", response);
    }
}