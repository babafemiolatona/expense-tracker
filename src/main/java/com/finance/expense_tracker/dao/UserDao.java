package com.finance.expense_tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.User;

public interface UserDao extends JpaRepository<User, Long> {

}
