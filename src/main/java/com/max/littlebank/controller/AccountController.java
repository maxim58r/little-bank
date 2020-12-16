package com.max.littlebank.controller;

import com.max.littlebank.DTO.AccountDTO;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Operation;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.Transfer;
import com.max.littlebank.service.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Serov Maxim
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public Account showAccountById(@PathVariable long id) {
        return accountService.findById(id);
    }

    @GetMapping
    public List<Account> showAllAccounts() {
        return accountService.showAllAccounts();
    }

    @DeleteMapping("/{id}")
    public String deleteAccountById(@PathVariable long id) {
        Account account = accountService.findById(id);
        if (account == null) {
            throw new NoSuchUserException("There is no user with account = " + id + " in DataBase");
        } else {
            accountService.deleteAccount(id);
        }
        return "User with id = " + id + " was deleted";
    }

    @PostMapping
    public void saveAccount(@RequestBody AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountService.saveAccount(account);
    }

    @PutMapping("/transfer")
    public String betweenAccountsTransfer(@RequestBody Transfer transfer) {
        accountService.betweenAccountsTransfer(transfer);
        return "Money transfer transaction completed";
    }

    @PutMapping("/withdraw")
    public String withdrawAccount(@RequestBody Transfer transfer) {
        accountService.withdrawAccount(transfer);
        return "Withdrawals have been completed";
    }

    @PutMapping("/obtain")
    public String obtainAccount(@RequestBody Transfer transfer) {
        accountService.obtainAccount(transfer);
        return "Obtain have been completed";
    }
}
