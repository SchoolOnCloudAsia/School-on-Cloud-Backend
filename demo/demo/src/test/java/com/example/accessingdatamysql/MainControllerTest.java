package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class MainControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testAddNewUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@test.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/demo/add")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Test")
                .param("email", "test@test.com"))
                .andExpect(status().isOk());

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setName("Test1");
        user1.setEmail("test1@test.com");

        User user2 = new User();
        user2.setName("Test2");
        user2.setEmail("test2@test.com");

        List<User> users = Arrays.asList(user1, user2);

        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/demo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/{id}", 1))
               .andExpect(status().isOk())
               .andExpect(content().string("Deleted"));
    }
}