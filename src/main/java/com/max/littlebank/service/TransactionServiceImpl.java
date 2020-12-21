package com.max.littlebank.service;

import com.max.littlebank.dao.TransactionDaoJpa;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.User;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Serov Maxim
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDaoJpa transactionDaoJpa;

    public TransactionServiceImpl(TransactionDaoJpa transactionDaoJpa) {
        this.transactionDaoJpa = transactionDaoJpa;
    }

    @Transactional
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionDaoJpa.save(transaction);
    }

    @Transactional
    @Override
    public void deleteAllByAccount_AccountNumber(long accountNumber) {
        transactionDaoJpa.deleteAllByAccount_AccountNumber(accountNumber);
    }

    public List<Transaction> showAllTransactions() {
        return transactionDaoJpa.findAll();
    }

    @Override
    public List<Transaction> findAllByAccount_AccountNumber(long accountNumber) {
        return transactionDaoJpa.findAllByAccount_AccountNumber(accountNumber);
    }

    @SneakyThrows
    @Override
    public List<Transaction> findByTransactionBetweenDate(String startDate, String endDate) {
        LocalDateTime localStartDate = LocalDateTime.parse(startDate);
        LocalDateTime localEndDate = LocalDateTime.parse(endDate);
        return transactionDaoJpa.findByDateTimeBetween(localStartDate,
                localEndDate);
    }


}
