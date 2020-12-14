package com.max.littlebank.service;

import com.max.littlebank.models.Transaction;

import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */
public interface TransactionService {
    void saveTransaction(Transaction transaction);

    void deleteTransaction(Transaction transaction);

    public List<Transaction> showAllTransactions();

    List<Transaction> findByTransactionBetweenDate(String startDate, String endDate);

}
