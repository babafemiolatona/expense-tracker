package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.Dto.CreateExpenseDto;
import com.finance.expense_tracker.dao.ExpenseDao;
import com.finance.expense_tracker.exceptions.ResourceNotFoundException;
import com.finance.expense_tracker.mapper.ExpenseMapper;
import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    public List<Expense> getAllExpenses(User user) {
        return expenseDao.findAllByUser(user);
    }

    public Expense getExpenseById(Long id, User user) {
        Expense expense = expenseDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        if (!expense.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to access this expense");
        }

        return expense;
    }

    public Expense addExpense(CreateExpenseDto dto, User user) {
        Expense expense = ExpenseMapper.fromDto(dto, user);
        return expenseDao.save(expense);
    }

    public Expense updateExpense(Long id, CreateExpenseDto updatedExpense, User user) {
        Expense existing = expenseDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        if (!existing.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to update this expense");
        }

        existing.setAmount(updatedExpense.getAmount());
        existing.setDescription(updatedExpense.getDescription());
        existing.setDate(updatedExpense.getDate());
        existing.setCategory(updatedExpense.getCategory());
        existing.setUser(user);

        return expenseDao.save(existing);
    }

    public void deleteExpense(Long id, User user) {
        Expense expense = expenseDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));

        if (!expense.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to delete this expense");
        }

        expenseDao.delete(expense);
    }
}

