package com.max.littlebank.service;

import com.max.littlebank.models.Account;
import com.max.littlebank.models.Operation;
import com.max.littlebank.models.Transfer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */
public interface AccountService {
    void saveAccount(Account account);

    Account findById(long id);

     void deleteAccount(long id);

    List<Account> showAllAccounts();

    void betweenAccountsTransfer(Transfer transfer);

    void withdrawAccount(Transfer transfer);

    void obtainAccount(Transfer transfer);
}

