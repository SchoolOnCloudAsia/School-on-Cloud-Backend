import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

public class MainControllerTest {

    @InjectMocks
    private MainController mainController;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    public void testAddAndRetrieveUserSuccess() throws Exception {
        // Arrange
        setup();
        String name = "John";
        String email = "john@example.com";

        // Mocking userRepository.save() method
        when(userRepository.save(any(User.class))).thenReturn(new User());

        // Mocking userRepository.findAll() method
        User user = new User();
        user.setName(name);
        user.setEmail(email);;
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        // Act (POST)
        mockMvc.perform(post("/demo/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", name)
                .param("email", email))
                .andExpect(status().isOk())
                .andExpect(content().string("Saved"));

        // Act and Assert (GET)
        mockMvc.perform(get("/demo/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[0].email", is("john@example.com")));
    }
}