package com.max.littlebank.service;

import com.max.littlebank.models.Transaction;

import java.util.List;

/**
 * @author Serov Maxim
 */
public interface TransactionService {
    void saveTransaction(Transaction transaction);

     void deleteAllByAccount_AccountNumber(long accountNumber);

     List<Transaction> showAllTransactions();

    List<Transaction> findByTransactionBetweenDate(String startDate, String endDate);

    List<Transaction> findAllByAccount_AccountNumber(long accountNumber);

}
