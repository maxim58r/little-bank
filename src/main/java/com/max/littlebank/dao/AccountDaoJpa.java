package com.max.littlebank.dao;

import com.max.littlebank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Serov Maxim
 */
public interface AccountDaoJpa extends JpaRepository<Account, Long> {

}
