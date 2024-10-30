package ua.edu.ukma.event_management_system.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.params.ParameterizedTest;

import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class SpringSecurityTest {

    @Autowired
    private MockMvc mvc;

    @LocalServerPort
    private int port;

//    @Autowired
//    private WebApplicationContext context;
//
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }

    @ParameterizedTest
    @ValueSource(strings = {"USER", "ADMIN", "ORGANIZER"})
    public void contextLoads(String role) throws Exception {
        mvc.perform(get("/FAQ.html").with(user("testUser").roles(role))).andExpect(status().isOk());
    }


    @Test
    @WithMockUser(authorities = {"ORGANIZER"}) //Just admin doesn't work, since security specifically requires ORGANIZER, but logic will make sure any admin is an organizer
    public void getUsers() throws Exception {
        mvc.perform(get("/user")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    public void isManagable() throws Exception {
        mvc.perform(get("/manage")).andExpect(status().isOk());
    }

}
