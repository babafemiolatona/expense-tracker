package com.finance.expense_tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;

public interface ExpenseDao extends JpaRepository<Expense, Long> {
    
    List<Expense> findByUserId(Long userId);
    List<Expense> findAllByUser(User user);

}
