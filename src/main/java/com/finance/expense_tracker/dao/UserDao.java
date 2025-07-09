package com.finance.expense_tracker.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.User;

public interface UserDao extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

}
