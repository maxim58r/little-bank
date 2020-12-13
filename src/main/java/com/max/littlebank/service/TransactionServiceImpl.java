package com.max.littlebank.service;

import com.max.littlebank.dao.TransactionDaoJpa;
import com.max.littlebank.models.Transaction;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Serov Maxim
 */
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDaoJpa transactionDaoJpa;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy'T'HH:mm");


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

    @SneakyThrows
    @Override
    public Transaction findByTransactionBetweenDate(String startDate, String endDate) {
        Date simpleStartDate = simpleDateFormat.parse(startDate);
        Date simpleEndDate = simpleDateFormat.parse(endDate);
        return transactionDaoJpa.findByDateTimeBetween(simpleStartDate,
                simpleEndDate);
    }
}
