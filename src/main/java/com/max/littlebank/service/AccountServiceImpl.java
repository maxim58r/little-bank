package com.max.littlebank.service;

import com.max.littlebank.dao.AccountDaoJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.TransferException;
import com.max.littlebank.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Serov Maxim
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDaoJpa accountDaoJpa;

    @Autowired
    TransactionService transactionService;

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

    public List<Account> showAllAccounts() {
        return accountDaoJpa.findAll();
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

    @Override
    public void betweenAccountsTransfer(Transfer transfer) {
        withdrawAccount(transfer);
        obtainAccount(transfer);
    }

    @Override
    public void withdrawAccount(Transfer transfer) {
        Account account = findById(transfer.getTransferFromId());
        Transaction transaction = getTransaction(account,
                transfer.getAmount(),
                "WITHDRAW");

        account.setAmount(account.getAmount().subtract(transfer.getAmount()));

        if (account.getAmount().signum() >= 0) {
            transactionService.saveTransaction(transaction);
        } else {
            throw new TransferException("Insufficient funds on the account");
        }
    }

    @Override
    public void obtainAccount(Transfer transfer) {
        Account account = findById(transfer.getTransferToId());
        Transaction transaction = getTransaction(account,
                transfer.getAmount(),
                "OBTAIN");

        account.setAmount(account.getAmount().add(transfer.getAmount()));
        transactionService.saveTransaction(transaction);
    }

    private Transaction getTransaction(Account account, BigDecimal amount, String type) {
        Transaction transaction = new Transaction();
        if (type.equals("WITHDRAW")) {
            transaction.setType(type);
            transaction.setAmount(amount.negate());
            transaction.setAccount(account);
        } else if (type.equals("OBTAIN")) {
            transaction.setType(type);
            transaction.setAmount(amount);
            transaction.setAccount(account);
        }
        return transaction;
    }
}
