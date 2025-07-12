package com.finance.expense_tracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.finance.expense_tracker.Dto.CreateIncomeDto;
import com.finance.expense_tracker.dao.IncomeDao;
import com.finance.expense_tracker.exceptions.ResourceNotFoundException;
import com.finance.expense_tracker.mapper.IncomeMapper;
import com.finance.expense_tracker.models.Income;
import com.finance.expense_tracker.models.User;

@Service
public class IncomeService {

    @Autowired
    private IncomeDao incomeDao;

    public List<Income> getAllIncomes(User user) {
        return incomeDao.findAllByUser(user);
    }

    public Income getIncomeById(Long id, User user) {
        Income income = incomeDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Income not found with id: " + id));
    
        if (!income.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to access this income");
        }
        return income;
    }

    public Income addIncome(CreateIncomeDto dto, User user) {
        Income income = IncomeMapper.fromDto(dto, user);
        return incomeDao.save(income);
    }

    public Income updateIncome(Long id, CreateIncomeDto updatedIncome, User user) {
        Income existing = incomeDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Income not found with id: " + id));

        if (!existing.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to update this income");
        }

        existing.setSource(updatedIncome.getSource());
        existing.setAmount(updatedIncome.getAmount());
        existing.setDate(updatedIncome.getDate());
        existing.setUser(user);

        return incomeDao.save(existing);
    }

    public void deleteIncome(Long id, User user) {
        Income income = incomeDao.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Income not found with id: " + id));

        if (!income.getUser().getId().equals(user.getId())) {
            throw new AccessDeniedException("Not allowed to delete this income");
        }

        incomeDao.delete(income);
    }

}
