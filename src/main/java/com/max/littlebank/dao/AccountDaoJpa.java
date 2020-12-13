package com.max.littlebank.dao;

import com.max.littlebank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * @author Serov Maxim
 */
public interface AccountDaoJpa extends JpaRepository<Account, Long> {
    Account findByTransactionsBetween(Date startDate, Date endDate);
}
