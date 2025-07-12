package com.finance.expense_tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.Budget;
import com.finance.expense_tracker.models.User;

public interface BudgetDao extends JpaRepository<Budget, Long> {

    List<Budget> findByUserId(Long userId);
    List<Budget> findAllByUser(User user);

}
