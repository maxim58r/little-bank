package com.max.littlebank.dao;

import com.max.littlebank.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Serov Maxim
 */
public interface AccountDaoJpa extends JpaRepository<Account, Long> {
    void deleteAllByOwner_Id(long id);

    List<Account> findAllByOwner_Id(long id);
}
