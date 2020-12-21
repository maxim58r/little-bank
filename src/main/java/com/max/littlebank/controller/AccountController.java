package com.max.littlebank.controller;

import com.max.littlebank.DTO.AccountDTO;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transfer;
import com.max.littlebank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    public List<Account> findAllByOwner_Id(@PathVariable long id) {
        return accountService.findAllByOwner_Id(id);
    }

    @GetMapping
    public List<Account> showAllAccounts() {
        return accountService.showAllAccounts();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteAllByOwner_Id(@PathVariable long id) {
        accountService.deleteAllByOwner_Id(id);
        return ResponseEntity.ok("Accounts with Owner_Id = " + id + " was deleted");
    }

    @PostMapping
    ResponseEntity<String>  saveAccount(@RequestBody AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountService.saveAccount(account);
        return ResponseEntity.ok("Account was created");
    }

    @PutMapping("/transfer")
    ResponseEntity<String> betweenAccountsTransfer(@RequestBody Transfer transfer) {
        accountService.betweenAccountsTransfer(transfer);
        return ResponseEntity.ok("Cash transfer transactions completed");
    }

    @PutMapping("/withdraw")
    ResponseEntity<String> withdrawAccount(@RequestBody Transfer transfer) {
        accountService.withdrawAccount(transfer);
        return ResponseEntity.ok("Withdrawals have been completed");
    }

    @PutMapping("/obtain")
    ResponseEntity<String> obtainAccount(@RequestBody Transfer transfer) {
        accountService.obtainAccount(transfer);
        return ResponseEntity.ok("Obtain have been completed");
    }
}
