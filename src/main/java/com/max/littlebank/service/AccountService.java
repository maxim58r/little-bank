package com.max.littlebank.service;

import com.max.littlebank.models.Account;

import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */
public interface AccountService {
    void saveAccount(Account account);

    Account findById(long id);

     void deleteAccount(long id);

    public List<Account> showAllAccounts();

}

