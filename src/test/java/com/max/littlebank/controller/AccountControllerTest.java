package com.max.littlebank.controller;

import com.max.littlebank.controller.AccountController;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.User;
import com.max.littlebank.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Serov Maxim
 */

@ActiveProfiles("test_account_controller")
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;


    @Test
    public void get_showAllAccounts_returnsOkWithListOfAccounts() throws Exception {
        when(accountService.showAllAccounts()).thenReturn(getNewListAccounts());

        this.mockMvc.perform(get("/accounts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].amount", is(1000)))
                .andExpect(jsonPath("$[1].amount", is(2000)))
                .andExpect(jsonPath("$[2].amount", is(3000)));
    }

    private List<Account> getNewListAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(Account.builder()
                .accountNumber(0)
                .amount(BigDecimal.valueOf(1000))
                .openingDate(LocalDateTime.parse("2020-12-20T20:50:37"))
                .validityPeriod(LocalDateTime.parse("2024-12-20T20:50:37"))
                .owner(getNewListUsers().get(0)).build());

        accounts.add(Account.builder()
                .accountNumber(1)
                .amount(BigDecimal.valueOf(2000))
                .openingDate(LocalDateTime.parse("2020-12-20T20:50:37"))
                .validityPeriod(LocalDateTime.parse("2024-12-20T20:50:37"))
                .owner(getNewListUsers().get(1)).build());

        accounts.add(Account.builder()
                .accountNumber(2)
                .amount(BigDecimal.valueOf(3000))
                .openingDate(LocalDateTime.parse("2020-12-20T20:50:37"))
                .validityPeriod(LocalDateTime.parse("2024-12-20T20:50:37"))
                .owner(getNewListUsers().get(2)).build());
        return accounts;
    }

    private List<User> getNewListUsers() {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .id(0)
                .fullname("test")
                .email("test@mail.ru")
                .phone("+7937-123-45-67")
                .address("penza")
                .dateOfBirth(LocalDate.parse("1999-12-11")).build());

        users.add(User.builder()
                .id(1)
                .fullname("max")
                .email("max@gmail.com")
                .phone("+7927-412-45-55")
                .address("new-York")
                .dateOfBirth(LocalDate.parse("2005-10-25")).build());

        users.add(User.builder()
                .id(2)
                .fullname("alex")
                .email("alex@yandex.ru")
                .phone("+7902-444-45-11")
                .address("saint-Petersburg")
                .dateOfBirth(LocalDate.parse("1982-12-13")).build());
        return users;
    }
}
