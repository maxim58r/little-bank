package com.max.littlebank.service;

import com.max.littlebank.repository.AccountRepositoryJpa;
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

    private final AccountRepositoryJpa accountRepositoryJpa;
    private final TransactionService transactionService;

    public AccountServiceImpl(AccountRepositoryJpa accountRepositoryJpa, TransactionService transactionService) {
        this.accountRepositoryJpa = accountRepositoryJpa;
        this.transactionService = transactionService;
    }

    @Transactional
    @Override
    public Account saveAccount(Account account) {
        account.setAccountNumber(0);
        account.setAmount(BigDecimal.ZERO);
        account.setOpeningDate(null);
        account.setValidityPeriod(null);
        return accountRepositoryJpa.save(account);
    }

    @Transactional
    @Override
    public void deleteAccount(long id) {
        transactionService.deleteAllByAccount_AccountNumber(id);
        accountRepositoryJpa.deleteById(id);
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
        accountRepositoryJpa.deleteAllByOwner_Id(id);
    }

    @Override
    public List<Account> findAllByOwner_Id(long id) {
        return accountRepositoryJpa.findAllByOwner_Id(id);
    }

    @Override
    public List<Account> showAllAccounts() {
        return accountRepositoryJpa.findAll();
    }

    @Override
    public Account findById(long id) {
        return Optional.of(accountRepositoryJpa.findById(id)).get()
                .orElseThrow(() -> new NoSuchUserException("There is no user with account = " + id + " in DataBase"));
    }

    @Transactional
    @Override
    public boolean betweenAccountsTransfer(Transfer transfer) {
        withdrawAccount(transfer);
        obtainAccount(transfer);
        return true;
    }

    @Transactional
    @Override
    public boolean withdrawAccount(Transfer transfer) {
        Account account = findById(transfer.getTransferFromId());
        Transaction transaction = getTransaction(account, transfer.getAmount(), TransactionType.WITHDRAW);

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
    public boolean obtainAccount(Transfer transfer) {
        Account account = findById(transfer.getTransferToId());
        Transaction transaction = getTransaction(account, transfer.getAmount(), TransactionType.OBTAIN);

        account.setAmount(account.getAmount().add(transfer.getAmount()));

        transactionService.saveTransaction(transaction);
        return true;
    }

    private Transaction getTransaction(Account account, BigDecimal amount, TransactionType type) {
        Transaction transaction = new Transaction();
        transaction.setType(type.toString());
        transaction.setAccount(account);

        if (type.toString().equals("WITHDRAW")) {
            transaction.setAmount(amount.negate());

        } else if (type.toString().equals("OBTAIN")) {
            transaction.setAmount(amount);
        }
        return transaction;
    }
}
