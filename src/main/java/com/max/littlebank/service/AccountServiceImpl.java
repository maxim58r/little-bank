package com.max.littlebank.service;

import com.max.littlebank.dao.AccountDaoJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.models.Account;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author Serov Maxim
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final  AccountDaoJpa accountDaoJpa;

    public AccountServiceImpl(AccountDaoJpa accountDaoJpa) {
        this.accountDaoJpa = accountDaoJpa;
    }

    @Override
    public void saveAccount(Account account) {
        accountDaoJpa.save(account);
    }

    @Override
    public void deleteAccount(long id) {
        accountDaoJpa.deleteById(id);
    }

    @Override
    public Account findByTransactionsBetween(Date startDate, Date endDate) {
        return accountDaoJpa.findByTransactionsBetween(startDate, endDate);
    }


    @Override
    public Account findById(long id) {
        Account account;
        Optional<Account> optionalAccount = accountDaoJpa.findById(id);
        if (optionalAccount.isPresent()) {
            account = optionalAccount.get();
        } else {
            throw new NoSuchUserException("There is no user with account = " + id + " in DataBase");
        }
        return account;
    }
}
