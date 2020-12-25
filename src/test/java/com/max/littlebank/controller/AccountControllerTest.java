package com.max.littlebank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.littlebank.repository.AccountRepositoryJpa;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transfer;
import com.max.littlebank.models.User;
import com.max.littlebank.service.AccountService;
import com.max.littlebank.service.TransactionService;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;

/**
 * @author Serov Maxim
 */

@ActiveProfiles("test_account_controller")
@WebMvcTest(AccountController.class)
//@SpringBootTest
public class AccountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    AccountService accountService;

    @MockBean
    AccountRepositoryJpa accountRepositoryJpa;

    @MockBean
    TransactionService transactionService;

    @Test
    public void get_showAllAccounts_returnsOkWithListOfAccounts() throws Exception {
        when(accountService.showAllAccounts()).thenReturn(getNewListAccounts());

        this.mockMvc.perform(get("/accounts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].amount", is(1000)))
                .andExpect(jsonPath("$[1].amount", is(2000)))
                .andExpect(jsonPath("$[2].amount", is(3000)));
    }

    @Test
    public void put_withdrawAccount_returnAccepted() throws Exception {
//        AccountService accountServiceSpy = spy(accountService);
//        doNothing().when(accountServiceSpy).withdrawAccount(transfer);

//        when(accountService.withdrawAccount(getTransferFromId())).thenReturn(true);
        given(accountService.withdrawAccount(getTransferFromId())).willReturn(true);

        this.mockMvc.perform(put("/accounts/withdraw")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    private List<Account> getNewListAccounts() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(0, BigDecimal.valueOf(1000), LocalDateTime.parse("2020-12-20T20:50:37"),
                LocalDateTime.parse("2024-12-20T20:50:37"), getNewListUsers().get(0)));
        accounts.add(new Account(1, BigDecimal.valueOf(2000), LocalDateTime.parse("2020-12-20T20:50:37"),
                LocalDateTime.parse("2024-12-20T20:50:37"), getNewListUsers().get(1)));
        accounts.add(new Account(2, BigDecimal.valueOf(3000), LocalDateTime.parse("2020-12-20T20:50:37"),
                LocalDateTime.parse("2024-12-20T20:50:37"), getNewListUsers().get(2)));
        return accounts;
    }

    private List<User> getNewListUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(0, null, "test", "test@mail.ru", "+7937-123-45-67",
                "Penza", LocalDate.parse("1999-12-11")));
        users.add(new User(1, null, "max", "max@gmail.com", "+7927-412-45-55",
                "New-York", LocalDate.parse("2005-10-25")));
        users.add(new User(2, null, "alex", "alex@yandex.ru", "+7902-444-45-11",
                "Saint-Petersburg", LocalDate.parse("1982-12-13")));
        return users;
    }

    private Transfer getTransferFromId() {
        return Transfer.builder().transferFromId(0).amount(BigDecimal.valueOf(300)).build();
    }

    private Transfer getTransferToId() {
        return Transfer.builder().transferToId(1).amount(BigDecimal.valueOf(500)).build();
    }

    private Transfer betweenAccountsTransfer() {
        return Transfer.builder().transferFromId(0).transferToId(1).amount(BigDecimal.valueOf(500)).build();
    }
}
