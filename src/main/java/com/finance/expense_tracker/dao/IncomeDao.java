package com.finance.expense_tracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.Income;
import com.finance.expense_tracker.models.User;

public interface IncomeDao extends JpaRepository<Income, Long> {

    List<Income> findByUserId(Long userId);
    List<Income> findAllByUser(User user);

}
