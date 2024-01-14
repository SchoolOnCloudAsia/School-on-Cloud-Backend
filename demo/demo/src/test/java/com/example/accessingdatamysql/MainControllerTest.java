package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MainControllerTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MainController mainController;

    private List<User> users;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        User user1 = new User();
        user1.setUserID("user1");
        user1.setV(1.0f);
        user1.setA(1.0f);
        user1.setK(1.0f);
        user1.setPassword("password1");

        User user2 = new User();
        user2.setUserID("user2");
        user2.setV(2.0f);
        user2.setA(2.0f);
        user2.setK(2.0f);
        user2.setPassword("password2");

        users = Arrays.asList(user1, user2);
    }

    @Test
    public void testAddNewUser() {
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        for (User user : users) {
            String response = mainController.addNewUser(user.getUserID(), user.getV(), user.getA(), user.getK(), user.getPassword());
            assertEquals("Saved", response);
        }
    }

    @Test
    public void testGetUser() {
        for (User user : users) {
            when(userRepository.findByUserID(user.getUserID())).thenReturn(Optional.of(user));

            Optional<User> responseUser = mainController.getUser(user.getUserID());

            assertTrue(responseUser.isPresent());
            assertEquals(user.getUserID(), responseUser.get().getUserID());
            assertEquals(user.getV(), responseUser.get().getV());
            assertEquals(user.getA(), responseUser.get().getA());
            assertEquals(user.getK(), responseUser.get().getK());
            assertEquals(user.getPassword(), responseUser.get().getPassword());
        }
    }

    @Test
    public void testDeleteUser() {
        for (User user : users) {
            when(userRepository.findByUserID(user.getUserID())).thenReturn(Optional.of(user));

            String response = mainController.deleteUser(user.getUserID());

            assertEquals("Deleted", response);
            verify(userRepository, times(1)).delete(user);
        }
    }
}