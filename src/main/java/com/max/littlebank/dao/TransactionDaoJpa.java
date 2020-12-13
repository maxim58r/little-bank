package com.max.littlebank.dao;

import com.max.littlebank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author Serov Maxim
 */
public interface TransactionDaoJpa extends JpaRepository<Transaction, Long> {
    Transaction findByDateTimeBetween(Date startDate, Date endDate);
}
