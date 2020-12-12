package com.max.littlebank.dao;

import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Serov Maxim
 */
public interface TransactionDaoJpa extends JpaRepository<Transaction, Long> {
}
