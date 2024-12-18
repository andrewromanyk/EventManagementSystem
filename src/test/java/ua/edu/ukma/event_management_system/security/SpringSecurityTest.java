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
class SpringSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;

    @ParameterizedTest
    @ValueSource(strings = {"USER", "ADMIN", "ORGANIZER"})
    void contextLoads(String role) throws Exception {
        mockMvc.perform(get("/FAQ.html").with(user("testUser").roles(role))).andExpect(status().isOk());
    }


    @Test
    @WithMockUser(authorities = {"ORGANIZER"}) //Just admin doesn't work, since security specifically requires ORGANIZER, but logic will make sure any admin is an organizer
    void getUsers() throws Exception {
        mockMvc.perform(get("/user/")).andExpect(status().isForbidden());
    }

    @Test//Just admin doesn't work, since security specifically requires ORGANIZER, but logic will make sure any admin is an organizer
    void getMain() throws Exception {
        mockMvc.perform(get("/main")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    void isManagable() throws Exception {
        mockMvc.perform(get("/manage")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = {"USER"})
    void userCantAccess() throws Exception {
        mockMvc.perform(get("/user")).andExpect(status().isForbidden());
    }

    @Test
    void testPublicEndpoints() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
        mockMvc.perform(get("/FAQ.html")).andExpect(status().isOk());
        mockMvc.perform(get("/styles/basic.css")).andExpect(status().isOk());
        mockMvc.perform(get("/main")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "USER")
    void testUserProtectedEndpoints() throws Exception {
        mockMvc.perform(get("/building/1")).andExpect(status().isOk());
        mockMvc.perform(get("/user/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ORGANIZER")
    void testOrganizerProtectedEndpoints() throws Exception {
        mockMvc.perform(get("/event/1")).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    void testAdminProtectedEndpoints() throws Exception {
        mockMvc.perform(get("/api/event/1")).andExpect(status().isOk());
    }

    @Test
    void testUnauthorizedAccess() throws Exception {
        mockMvc.perform(get("/myprofile")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/building")).andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/manage/cache")).andExpect(status().is3xxRedirection());
    }

}
