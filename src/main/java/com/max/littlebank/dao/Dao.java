package com.max.littlebank.dao;

import com.max.littlebank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Dao extends JpaRepository<User, Long> {
}
