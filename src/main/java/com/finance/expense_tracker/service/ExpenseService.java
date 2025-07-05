package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.dao.ExpenseDao;
import com.finance.expense_tracker.exceptions.ResourceNotFoundException;
import com.finance.expense_tracker.models.Expense;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    public List<Expense> getAllExpenses() {
        return expenseDao.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public Expense addExpense(Expense expense) {
        return expenseDao.save(expense);
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existing = expenseDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        existing.setAmount(updatedExpense.getAmount());
        existing.setDescription(updatedExpense.getDescription());
        existing.setCategory(updatedExpense.getCategory());
        existing.setDate(updatedExpense.getDate());

        return expenseDao.save(existing);
    }

    public void deleteExpense(Long id) {
        if (expenseDao.existsById(id)) {
            expenseDao.deleteById(id);
        } else {
            throw new RuntimeException("Expense not found with id: " + id);
        }
    }
    
}
