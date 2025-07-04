package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.dao.ExpenseDao;
import com.finance.expense_tracker.models.Expense;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    private List<Expense> getAllExpenses() {
        return expenseDao.findAll();
    }

    private Expense addExpense(Expense expense) {
        return expenseDao.save(expense);
    }

}
