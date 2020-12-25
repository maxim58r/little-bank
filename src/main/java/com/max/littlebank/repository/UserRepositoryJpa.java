package com.max.littlebank.repository;

import com.max.littlebank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {
   User findByPhone(String number);

    User findByFullname(String fullname);

    User findByEmail(String email);

}
