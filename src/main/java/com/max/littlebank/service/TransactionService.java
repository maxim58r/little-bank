package com.max.littlebank.service;

import com.max.littlebank.models.Transaction;

import java.util.Date;

/**
 * @author Serov Maxim
 */
public interface TransactionService {
    void saveTransaction(Transaction transaction);
    void deleteTransaction(Transaction transaction);
    Transaction findByTransactionBetweenDate(String startDate, String endDate);

}
