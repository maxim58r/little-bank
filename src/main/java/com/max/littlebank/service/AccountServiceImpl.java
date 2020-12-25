package com.max.littlebank.service;

import com.max.littlebank.dao.AccountDaoJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.TransferException;
import com.max.littlebank.models.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author Serov Maxim
 */
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountDaoJpa accountDaoJpa;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountDaoJpa accountDaoJpa, TransactionService transactionService) {
        this.accountDaoJpa = accountDaoJpa;
        this.transactionService = transactionService;
    }

    @Transactional
    @Override
    public void saveAccount(Account account) {
        account.setAccountNumber(0);
        account.setAmount(BigDecimal.ZERO);
        account.setOpeningDate(null);
        account.setValidityPeriod(null);
        accountDaoJpa.save(account);
    }

    @Transactional
    @Override
    public void deleteAccount(long id) {
        transactionService.deleteAllByAccount_AccountNumber(id);
        accountDaoJpa.deleteById(id);
    }

    @Transactional
    @Override
    public void deleteAllByOwner_Id(long id) {
        Account account = findById(id);
        if (account == null) {
            throw new NoSuchUserException("There is no account = " + id + " in DataBase");
        } else {

            List<Account> accounts = findAllByOwner_Id(id);
            for (var accountVar : accounts) {
                transactionService.deleteAllByAccount_AccountNumber(accountVar.getAccountNumber());
            }
        }
        accountDaoJpa.deleteAllByOwner_Id(id);
    }

    @Override
    public List<Account> findAllByOwner_Id(long id) {
        return accountDaoJpa.findAllByOwner_Id(id);
    }

    @Override
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

    @Transactional
    @Override
    public void betweenAccountsTransfer(Transfer transfer) {
        withdrawAccount(transfer);
        obtainAccount(transfer);
    }

    @Transactional
    @Override
    public boolean withdrawAccount(Transfer transfer) {
        Account account = findById(transfer.getTransferFromId());
        Transaction transaction = getTransaction(account,
                transfer.getAmount(),
                "WITHDRAW");

        account.setAmount(account.getAmount().subtract(transfer.getAmount()));

        if (account.getAmount().signum() >= 0) {
            transactionService.saveTransaction(transaction);
            return true;
        } else {
            throw new TransferException("Insufficient funds on the account");
        }
    }

    @Transactional
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
