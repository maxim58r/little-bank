package com.max.littlebank.service;

import com.max.littlebank.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Serov Maxim
 */

@SpringBootTest
public class UserServiceTest {

    @MockBean
    private UserService userService;


    @Test
    void saveUser() {
        User user = getNewUser();
        given(userService.saveUser(user)).willReturn(user);
        User savedUser = userService.saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test
    public void updateUser() {
        User user = getNewUser();
        given(userService.updateUser(user)).willReturn(user);
        User updateUser = userService.updateUser(user);
        assertEquals(user, updateUser);
    }


    @Test
    void findById() {
        long id = 1L;
        given(userService.findById(id)).willReturn(getNewUser());
        User findByIdUser = userService.findById(id);
        assertEquals(getNewUser(), findByIdUser);
    }

    @Test
    void findByPhone() {
        String phone = "+7937-123-45-67";
        given(userService.findByPhone(phone)).willReturn(getNewUser());
        User findByPhoneUser = userService.findByPhone(phone);
        assertEquals(getNewUser(), findByPhoneUser);
    }

    @Test
    void findByFullname() {
        String fullname = "test";
        given(userService.findByPhone(fullname)).willReturn(getNewUser());
        User findByFullnameUser = userService.findByPhone(fullname);
        assertEquals(getNewUser(), findByFullnameUser);
    }

    @Test
    void findByEmail() {
        String email = "test@mail.ru";
        given(userService.findByPhone(email)).willReturn(getNewUser());
        User findByEmailUser = userService.findByPhone(email);
        assertEquals(getNewUser(), findByEmailUser);
    }

    @Test
    void deleteById() {
        long id = 1L;
        UserService userServiceMock = mock(UserService.class);
        doNothing().when(userServiceMock).deleteById(id);
        userServiceMock.deleteById(id);
        verify(userServiceMock, times(1)).deleteById(id);
    }

    @Test
    void findAll() {
        List<User> users = new ArrayList<>(Collections.singleton(getNewUser()));
        given(userService.findAll()).willReturn(users);
       List<User> findAllUsers = userService.findAll();
        assertEquals(users, findAllUsers);
    }

    private User getNewUser() {
        return User.builder()
                .id(1)
                .fullname("test")
                .email("test@mail.ru")
                .phone("+7937-123-45-67")
                .address("penza")
                .dateOfBirth(LocalDate.parse("1999-12-11")).build();
    }
}
