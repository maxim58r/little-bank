package com.max.littlebank.service;

import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transfer;

import java.util.List;

/**
 * @author Serov Maxim
 */
public interface AccountService {
    Account saveAccount(Account account);

    Account findById(long id);

    List<Account> showAllAccounts();

    List<Account> findAllByOwner_Id(long id);

     void deleteAccount(long id);

    void deleteAllByOwner_Id(long id);

    boolean betweenAccountsTransfer(Transfer transfer);

    boolean withdrawAccount(Transfer transfer);

    boolean obtainAccount(Transfer transfer);
}

