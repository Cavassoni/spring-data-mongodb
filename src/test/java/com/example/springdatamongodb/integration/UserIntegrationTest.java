package com.example.springdatamongodb.integration;

import com.example.springdatamongodb.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@Rollback
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserIntegrationTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    protected MockMvc mockMvc;

    private final String BASE_URL = "/api/user";

    @Test
    public void getAllUsers() throws Exception {
        mockMvc
                .perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Rollback
    public void insertUser() throws Exception {
        var user = User.builder()
                .name("John Doe")
                .email("john@example.com")
                .build();

        mockMvc
                .perform(post(BASE_URL)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(user.getName()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    public void getUserByIdNotFounb() throws Exception {
        final String userId = "654fa412529e6c482f53f4a8";

        mockMvc
                .perform(get(BASE_URL + "/" + userId))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    public void getExistingUser() throws Exception {
        var user = User.builder()
                .name("John Doe")
                .email("john@example.com")
                .build();

        var contentAsString = mockMvc
                .perform(post(BASE_URL)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(user)))
                .andReturn()
                .getResponse() //
                .getContentAsString();
        final var insertedUser = objectMapper.readValue(contentAsString, User.class);

        mockMvc
                .perform(get(BASE_URL + "/" + insertedUser.getId()))
                .andExpect(status().isOk())
                .andDo(print());
    }

}