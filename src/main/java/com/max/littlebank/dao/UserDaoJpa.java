package com.max.littlebank.dao;

import com.max.littlebank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDaoJpa extends JpaRepository<User, Long> {
    User findByPhone(String number);

    User findByFullname(String fullname);

    User findByEmail(String email);

}
