package com.max.littlebank.service;

import com.max.littlebank.dao.TransactionDaoJpa;
import com.max.littlebank.models.Transaction;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Serov Maxim
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDaoJpa transactionDaoJpa;
//    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public TransactionServiceImpl(TransactionDaoJpa transactionDaoJpa) {
        this.transactionDaoJpa = transactionDaoJpa;
    }

    @Override
    public void saveTransaction(Transaction transaction) {
        transactionDaoJpa.save(transaction);
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionDaoJpa.delete(transaction);
    }

    public List<Transaction> showAllTransactions() {
       return transactionDaoJpa.findAll();
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
