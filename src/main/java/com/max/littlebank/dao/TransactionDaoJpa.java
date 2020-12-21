package com.max.littlebank.dao;

import com.max.littlebank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Serov Maxim
 */
@Repository
public interface TransactionDaoJpa extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

     void deleteAllByAccount_AccountNumber(long accountNumber);

    List<Transaction> findAllByAccount_AccountNumber(long accountNumber);
}