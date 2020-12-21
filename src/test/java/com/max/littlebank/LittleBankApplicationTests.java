package com.max.littlebank;

import com.max.littlebank.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LittleBankApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserController userController;

    @Test
    void contextLoads() {
        assertThat(userController).isNotNull();
    }

    @Test
    public void shouldReturnsShowUserById() throws Exception {
        this.mockMvc.perform(get("/users/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "{id:1," +
                                "image:null," +
                                "fullname:dmitri," +
                                "email:dimitri@mail.com," +
                                "phone:+7937-666-66-44," +
                                "address:samara," +
                                "dateOfBirth:1990-04-25}")));
    }

    @Test
    public void addNewUser() throws Exception {
       this.mockMvc.perform(multipart("/users")
       );
    }
}

