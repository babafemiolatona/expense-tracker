package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.Dto.CreateExpenseDto;
import com.finance.expense_tracker.dao.CategoryDao;
import com.finance.expense_tracker.dao.ExpenseDao;
import com.finance.expense_tracker.dao.UserDao;
import com.finance.expense_tracker.exceptions.ResourceNotFoundException;
import com.finance.expense_tracker.mapper.ExpenseMapper;
import com.finance.expense_tracker.models.Category;
import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    public List<Expense> getAllExpenses() {
        return expenseDao.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseDao.findById(id)
            .orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public Expense addExpense(CreateExpenseDto dto) {
        User user = userDao.findById(dto.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Category category = categoryDao.findById(dto.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        Expense expense = ExpenseMapper.fromDto(dto, user, category);
        return expenseDao.save(expense);
    }

    public Expense updateExpense(Long id, CreateExpenseDto updatedExpense) {
        Expense existing = expenseDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Expense not found with id: " + id));

        User user = userDao.findById(updatedExpense.getUserId())
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Category category = categoryDao.findById(updatedExpense.getCategoryId())
            .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        existing.setAmount(updatedExpense.getAmount());
        existing.setDescription(updatedExpense.getDescription());
        existing.setDate(updatedExpense.getDate());
        existing.setCategory(category);
        existing.setUser(user);

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
