package com.max.littlebank.dao;

import com.max.littlebank.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDaoJpa extends JpaRepository<User, Long> {
    User findByPhone(String number);

    User findAllByFullname(String fullname);

    User findByEmail(String email);


}
