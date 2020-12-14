package com.max.littlebank.dao;

import com.max.littlebank.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */
public interface TransactionDaoJpa extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
