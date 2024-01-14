package com.example.accessingdatamysql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  private User user1;
  private User user2;

  @BeforeEach
  public void setup() {
    user1 = new User();
    user1.setId(1);
    user1.setDateTime(new Date());
    user1.setUserID("user1");
    user1.setPassword("password1");
    user1.setA(1.0f);
    user1.setK(1.0f);
    user1.setV(1.0f);

    user2 = new User();
    user2.setId(2);
    user2.setDateTime(new Date());
    user2.setUserID("user2");
    user2.setPassword("password2");
    user2.setA(2.0f);
    user2.setK(2.0f);
    user2.setV(2.0f);
  }

  @Test
  public void testAddNewUser() throws Exception {
    when(userRepository.save(any(User.class))).thenReturn(user1);

    mockMvc.perform(post("/demo/add")
        .param("UserID", "user1")
        .param("Password", "password1")
        .param("A", "1.0")
        .param("K", "1.0")
        .param("V", "1.0")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Saved"));
  }

  @Test
  public void testGetAllUsers() throws Exception {
    List<User> users = Arrays.asList(user1, user2);
    when(userRepository.findAll()).thenReturn(users);

    mockMvc.perform(get("/demo/all")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].userID").value(user1.getUserID()))
        .andExpect(jsonPath("$[0].password").value(user1.getPassword()))
        .andExpect(jsonPath("$[1].userID").value(user2.getUserID()))
        .andExpect(jsonPath("$[1].password").value(user2.getPassword()));
  }

  @Test
  public void testDeleteUser() throws Exception {
    when(userRepository.findById(1)).thenReturn(Optional.of(user1));

    mockMvc.perform(delete("/demo/delete")
        .param("id", "1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("Deleted"));
  }

  @Test
  public void testDeleteUserNotFound() throws Exception {
    when(userRepository.findById(1)).thenReturn(Optional.empty());

    mockMvc.perform(delete("/demo/delete")
        .param("id", "1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string("User with id 1 does not exist"));
  }
}