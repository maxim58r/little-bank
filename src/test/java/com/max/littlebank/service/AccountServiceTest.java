package com.max.littlebank.service;

import com.max.littlebank.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * @author Serov Maxim
 */

@SpringBootTest
public class AccountServiceTest {

    @MockBean
    private AccountService accountService;

    @MockBean
    private TransactionService transactionService;

    @Test
    public void saveAccount() {
        Account account = getNewListAccounts().get(0);
        given(accountService.saveAccount(account)).willReturn(account);

        Account savedAccount = accountService.saveAccount(account);
        assertEquals(account, savedAccount);
    }

    @Test
    public void deleteAccount() {


    }

    @Test
    public void withdrawAccount() {
         var transfer = getTransferFromId();

        given(accountService.withdrawAccount(transfer)).willReturn(true);
        boolean isWithdrawAccount = accountService.withdrawAccount(transfer);

        assertTrue(isWithdrawAccount);
//        verify(transactionService, times(1)).saveTransaction();

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

    private Transfer getTransferFromId() {
        return Transfer.builder().transferFromId(0).amount(BigDecimal.valueOf(300)).build();
    }

    private Transfer getTransferToId() {
        return Transfer.builder().transferToId(1).amount(BigDecimal.valueOf(500)).build();
    }

    private Transfer betweenAccountsTransfer() {
        return Transfer.builder().transferFromId(0).transferToId(1).amount(BigDecimal.valueOf(500)).build();
    }

    private Transaction getTransaction(Account account, BigDecimal amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setType(type.toString());
        transaction.setAccount(account);

        if (type.toString().equals("WITHDRAW")) {
            transaction.setAmount(amount.negate());

        } else if (type.toString().equals("OBTAIN")) {
            transaction.setAmount(amount);
        }
        return transaction;
    }
}