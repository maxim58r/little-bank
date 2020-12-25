package com.max.littlebank.controller;

import com.max.littlebank.DTO.AccountDTO;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transfer;
import com.max.littlebank.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    public ResponseEntity<List<Account>> findAllByOwner_Id(@PathVariable long id) {
        List<Account> accounts = accountService.findAllByOwner_Id(id);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Account>> showAllAccounts() {
        List<Account> accounts = accountService.showAllAccounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAllByOwner_Id(@PathVariable long id) {
        accountService.deleteAllByOwner_Id(id);
        return new ResponseEntity<>("Accounts with Owner_Id = " + id + " was deleted",
                HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<String> saveAccount(@RequestBody AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountService.saveAccount(account);
        return new ResponseEntity<>("Account was created", HttpStatus.CREATED);
    }

    @PutMapping("/transfer")
    public ResponseEntity<String> betweenAccountsTransfer(@RequestBody Transfer transfer) {
        accountService.betweenAccountsTransfer(transfer);
        return new ResponseEntity<>("Cash transfer transactions completed",
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/withdraw")
    public ResponseEntity<String> withdrawAccount(@RequestBody Transfer transfer) {
        accountService.withdrawAccount(transfer);
        return new ResponseEntity<>("Withdrawals have been completed",
                HttpStatus.ACCEPTED);
    }

    @PutMapping("/obtain")
    public ResponseEntity<String> obtainAccount(@RequestBody Transfer transfer) {
        accountService.obtainAccount(transfer);
        return new ResponseEntity<>("Obtain have been completed",
                HttpStatus.ACCEPTED);
    }
}
