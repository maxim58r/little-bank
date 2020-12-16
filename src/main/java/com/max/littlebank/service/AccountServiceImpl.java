package com.max.littlebank.service;

import com.max.littlebank.dao.AccountDaoJpa;
import com.max.littlebank.exeption_handing.NoSuchUserException;
import com.max.littlebank.exeption_handing.TransferException;
import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.Transfer;
import com.max.littlebank.models.Types;
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
        Account accountFrom = findById(transfer.getTransferFromId());
        Account accountTo = findById(transfer.getTransferToId());

        Transaction transactionFrom = new Transaction();
        transactionFrom.setType("TRANSFER");
        transactionFrom.setAmount(transfer.getAmount().negate());
        transactionFrom.setAccount(accountFrom);

        Transaction transactionTo = new Transaction();
        transactionTo.setType("OBTAIN");
        transactionTo.setAmount(transfer.getAmount());
        transactionTo.setAccount(accountTo);

        accountFrom.setAmount(accountFrom.getAmount().subtract(transfer.getAmount()));
        BigDecimal a = accountFrom.getAmount();

        if (a.signum() >= 0) {
            accountTo.setAmount(accountTo.getAmount().add(transfer.getAmount()));
            transactionService.saveTransaction(transactionFrom);
            transactionService.saveTransaction(transactionTo);
        } else {
            throw new TransferException("Insufficient funds on the account");
        }
    }
}
