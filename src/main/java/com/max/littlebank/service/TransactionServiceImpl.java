package com.max.littlebank.service;

import com.max.littlebank.repository.TransactionRepositoryJpa;
import com.max.littlebank.models.Transaction;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Serov Maxim
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepositoryJpa transactionRepositoryJpa;

    public TransactionServiceImpl(TransactionRepositoryJpa transactionRepositoryJpa) {
        this.transactionRepositoryJpa = transactionRepositoryJpa;
    }

    @Transactional
    @Override
    public void saveTransaction(Transaction transaction) {
        transactionRepositoryJpa.save(transaction);
    }

    @Transactional
    @Override
    public void deleteAllByAccount_AccountNumber(long accountNumber) {
        transactionRepositoryJpa.deleteAllByAccount_AccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> showAllTransactions() {
        return transactionRepositoryJpa.findAll();
    }

    @Override
    public List<Transaction> findAllByAccount_AccountNumber(long accountNumber) {
        return transactionRepositoryJpa.findAllByAccount_AccountNumber(accountNumber);
    }

    @SneakyThrows
    @Override
    public List<Transaction> findByTransactionBetweenDate(String startDate, String endDate) {
        LocalDate localStart = LocalDate.parse(startDate);
        LocalDateTime localDateTimeStart = localStart.atStartOfDay();
        LocalDate localEnd = LocalDate.parse(endDate);
        LocalDateTime localDateTimeEnd = localEnd.atTime(23,59);
        return transactionRepositoryJpa.findByDateTimeBetween(localDateTimeStart,
                localDateTimeEnd);
    }
}
