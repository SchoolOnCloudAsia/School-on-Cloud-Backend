package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
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
        user1.setId(1);
        user1.setVariableV("v1");
        user1.setVariableA("a1");
        user1.setVariableK("k1");

        user2 = new User();
        user2.setId(2);
        user2.setVariableV("v2");
        user2.setVariableA("a2");
        user2.setVariableK("k2");

        Arrays.asList(user1, user2);
    }

    @Test
    public void testAddNewUser() {
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        String response1 = mainController.addNewUser(String.valueOf(user1.getId()), user1.getVariableV(), user1.getVariableA(), user1.getVariableK());
        String response2 = mainController.addNewUser(String.valueOf(user2.getId()), user2.getVariableV(), user2.getVariableA(), user2.getVariableK());

        assertEquals("Saved", response1);
        assertEquals("Saved", response2);
    }

    @Test
    public void testGetUser() {
        when(userRepository.findById(user1.getId())).thenReturn(java.util.Optional.ofNullable(user1));
        when(userRepository.findById(user2.getId())).thenReturn(java.util.Optional.ofNullable(user2));

        User responseUser1 = mainController.getUser(String.valueOf(user1.getId()));
        User responseUser2 = mainController.getUser(String.valueOf(user2.getId()));

        assertEquals(user1.getId(), responseUser1.getId());
        assertEquals(user1.getVariableV(), responseUser1.getVariableV());
        assertEquals(user1.getVariableA(), responseUser1.getVariableA());
        assertEquals(user1.getVariableK(), responseUser1.getVariableK());

        assertEquals(user2.getId(), responseUser2.getId());
        assertEquals(user2.getVariableV(), responseUser2.getVariableV());
        assertEquals(user2.getVariableA(), responseUser2.getVariableA());
        assertEquals(user2.getVariableK(), responseUser2.getVariableK());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(user1.getId());
        doNothing().when(userRepository).deleteById(user2.getId());

        String response1 = mainController.deleteUser(String.valueOf(user1.getId()));
        String response2 = mainController.deleteUser(String.valueOf(user2.getId()));

        assertEquals("Deleted", response1);
        assertEquals("Deleted", response2);
    }
}