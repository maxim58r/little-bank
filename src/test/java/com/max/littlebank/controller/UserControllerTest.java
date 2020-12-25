package com.max.littlebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.littlebank.repository.UserRepositoryJpa;
import com.max.littlebank.models.User;
import com.max.littlebank.service.AccountService;
import com.max.littlebank.service.StorageService;
import com.max.littlebank.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Serov Maxim
 */
@ActiveProfiles("test")
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    StorageService storageService;

    @MockBean
    UserService userService;

    @MockBean
    AccountService accountService;

    @MockBean
    UserRepositoryJpa userRepositoryJpa;

    @Test
    public void get_allUsers_returnsOkWithListOfUsers() throws Exception {

        Mockito.when(userService.findAll()).thenReturn(getNewListUsers());

        mockMvc.perform(MockMvcRequestBuilders.get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].email", is("test@mail.ru")))
                .andExpect(jsonPath("$[0].phone", is("+7937-123-45-67")))
                .andExpect(jsonPath("$[1].fullname", is("max")))
                .andExpect(jsonPath("$[1].dateOfBirth", is("2005-10-25")))
                .andExpect(jsonPath("$[2].address", is("Saint-Petersburg")));
    }

    @Test
    public void post_saveNewUser() throws Exception {
        User user = getNewUser();

        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);
        userService.saveUser(user);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8").content(this.objectMapper.writeValueAsBytes(user));

        mockMvc.perform(builder).andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("test@mail.com")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(user)));
    }

    @Test
    public void post_submitsInvalidUser_WithEmptyFullname_Returns400() throws Exception {
        User user = getNewUserWithEmptyFieldFullname();

        String userJsonString = this.objectMapper.writeValueAsString(user);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON).content(userJsonString)).andExpect(status().isBadRequest());

        assertEquals(MethodArgumentNotValidException.class,
                Objects.requireNonNull(resultActions
                        .andReturn().getResolvedException()).getClass());
        assertTrue(Objects.requireNonNull(resultActions
                .andReturn().getResolvedException()).getMessage().contains("Name is mandatory"));
    }

    @Test
    public void put_updatesAndReturnsUpdatedObjWith202() throws Exception {
        User user = getNewUser();

        Mockito.when(userService.updateUser(user)).thenReturn(user);

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
                .put("/users", user).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
                .content(this.objectMapper.writeValueAsBytes(user));

        mockMvc.perform(builder).andExpect(status().isAccepted())
                .andExpect(jsonPath("$.fullname", is("test")))
                .andExpect(MockMvcResultMatchers.content().string(this.objectMapper.writeValueAsString(user)));
    }

    @Test
    public void delete_deleteUser_Returns204Status() throws Exception {
        long userId = 1L;

        UserService serviceSpy = Mockito.spy(userService);
        doNothing().when(serviceSpy).deleteById(userId);

        mockMvc.perform(MockMvcRequestBuilders.delete("/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(userService, times(1)).deleteById(userId);
    }


    @Test
    public void get_userByPhone_Returns200Status() throws Exception {
        String numberPhone = "+7937-123-45-67";

        Mockito.when(userService.findByPhone(numberPhone))
                .thenReturn(getNewUser());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/phone/" + numberPhone).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(userService, times(1)).findByPhone(numberPhone);
    }


    private User getNewUser() {
        return new User(0, null, "test", "test@mail.com", "+7937-123-45-67", "Penza", LocalDate.parse("1999-12-11"));
    }

    private User getNewUserWithEmptyFieldFullname() {
        return new User(0, null, "", "test@mail.com", "+7937-123-45-67", "Penza", LocalDate.parse("1999-12-11"));
    }

    private List<User> getNewListUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(0, null, "test", "test@mail.ru", "+7937-123-45-67", "Penza", LocalDate.parse("1999-12-11")));
        users.add(new User(1, null, "max", "max@gmail.com", "+7927-412-45-55", "New-York", LocalDate.parse("2005-10-25")));
        users.add(new User(2, null, "alex", "alex@yandex.ru", "+7902-444-45-11", "Saint-Petersburg", LocalDate.parse("1982-12-13")));
        return users;
    }


}