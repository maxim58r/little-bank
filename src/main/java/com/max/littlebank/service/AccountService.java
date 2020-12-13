package com.max.littlebank.service;

import com.max.littlebank.models.Account;

import java.util.Date;

/**
 * @author Serov Maxim
 */
public interface AccountService {
    void saveAccount(Account account);

    Account findById(long id);

     void deleteAccount(long id);

    Account findByTransactionsBetween(Date startDate, Date endDate);
}

