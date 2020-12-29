package com.max.littlebank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.littlebank.LittleBankApplication;
import com.max.littlebank.repository.UserRepositoryJpa;
import com.max.littlebank.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Serov Maxim
 */

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = LittleBankApplication.class)
@ActiveProfiles("integration")
public class UserControllerTestRestTemplate {

    final private static int port = 8080;
    final private static String baseUrl = "http://localhost:";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    UserRepositoryJpa userRepositoryJpa;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void get_allUsers_ReturnsAllUsers_OK() {

        List<String> expectedFullname = Stream.of("test", "max", "alex").collect(Collectors.toList());

        ResponseEntity<List<User>> responseEntity = this.testRestTemplate.exchange(baseUrl + port + "/users",
                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        List<User> usersResponseList = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assert usersResponseList != null;
        assertTrue(usersResponseList.size() > 3);
        assertTrue(usersResponseList.stream().anyMatch((user) -> expectedFullname.contains(user.getFullname())));
    }

    @Test
    public void get_userById_Returns_NotFound_404() {

        long id = 16L;
        ResponseEntity<String> result = this.testRestTemplate.exchange(baseUrl + port + "/users/" + id,
                HttpMethod.GET, null, String.class);
        JsonNode jsonTree = null;
        try {
            jsonTree = objectMapper.readTree(result.getBody());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JsonNode jsonNode = jsonTree.get("errorMessage");
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
