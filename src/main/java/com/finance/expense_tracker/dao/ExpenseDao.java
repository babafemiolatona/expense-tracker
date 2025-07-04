package com.finance.expense_tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.Expense;

public interface ExpenseDao extends JpaRepository<Expense, Long> {
    
}
