package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.Arrays;
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
        user1.setUserID("1");
        user1.setPassword("password1");
        user1.setA(1.0f);
        user1.setK(1.0f);
        user1.setV(1.0f);

        user2 = new User();
        user2.setUserID("2");
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

        assertEquals(users, Arrays.asList(user1, user2));
    }

    @Test
    public void testDeleteUser() {
        when(userRepository.findById(user1.getUserID())).thenReturn(Optional.of(user1));
        when(userRepository.findById(user2.getUserID())).thenReturn(Optional.of(user2));
        doNothing().when(userRepository).deleteById(user1.getUserID());
        doNothing().when(userRepository).deleteById(user2.getUserID());
    
        Optional<String> response1 = mainController.deleteUser(user1.getUserID());
        Optional<String> response2 = mainController.deleteUser(user2.getUserID());
    
        assertEquals("Deleted", response1.orElse("Not Found"));
        assertEquals("Deleted", response2.orElse("Not Found"));
    }
}