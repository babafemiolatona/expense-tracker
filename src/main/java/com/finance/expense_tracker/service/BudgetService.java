package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.Dto.CreateBudgetDto;
import com.finance.expense_tracker.dao.BudgetDao;
import com.finance.expense_tracker.exceptions.ResourceNotFoundException;
import com.finance.expense_tracker.mapper.BudgetMapper;
import com.finance.expense_tracker.models.Budget;
import com.finance.expense_tracker.models.User;

@Service
public class BudgetService {

    @Autowired
    private BudgetDao budgetDao;
    
    public List<Budget> getAllBudgets(User user) {
        return budgetDao.findAllByUser(user);
    }

    public Budget getBudgetById(Long id, User user) {
        Budget budget = budgetDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Budget not found with id: " + id));

        if (!budget.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to access this budget");
        }

        return budget;
    }

    public Budget addBudget(CreateBudgetDto dto, User user) {
        Budget budget = BudgetMapper.fromDto(dto, user);
        return budgetDao.save(budget);
    }

    public Budget updateBudget(Long id, CreateBudgetDto updatedBudget, User user) {
        Budget existing = budgetDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Budget not found with id: " + id));

        if (!existing.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to update this budget");
        }

        existing.setLimitAmount(updatedBudget.getLimitAmount());
        existing.setCategory(updatedBudget.getCategory());
        existing.setStartDate(updatedBudget.getStartDate());
        existing.setEndDate(updatedBudget.getEndDate());
        existing.setUser(user);

        return budgetDao.save(existing);
    }

    public void deleteBudget(Long id, User user) {
        Budget budget = budgetDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Budget not found with id: " + id));

        if (!budget.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to delete this budget");
        }

        budgetDao.delete(budget);
    }
}
