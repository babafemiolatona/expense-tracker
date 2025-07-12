package com.finance.expense_tracker.mapper;

import com.finance.expense_tracker.Dto.BudgetDto;
import com.finance.expense_tracker.Dto.CreateBudgetDto;
import com.finance.expense_tracker.models.Budget;
import com.finance.expense_tracker.models.User;

public class BudgetMapper {

    public static BudgetDto toDto(Budget budget) {
        BudgetDto dto = new BudgetDto();
        dto.setId(budget.getId());
        dto.setCategory(budget.getCategory());
        dto.setLimitAmount(budget.getLimitAmount());
        dto.setStartDate(budget.getStartDate());
        dto.setEndDate(budget.getEndDate());
        dto.setCreatedAt(budget.getCreatedAt());
        dto.setUpdatedAt(budget.getUpdatedAt());
        return dto;
    }

    public static Budget fromDto(CreateBudgetDto createBudgetDto, User user) {
        Budget budget = new Budget();
        budget.setLimitAmount(createBudgetDto.getLimitAmount());
        budget.setCategory(createBudgetDto.getCategory());
        budget.setStartDate(createBudgetDto.getStartDate());
        budget.setEndDate(createBudgetDto.getEndDate());
        budget.setUser(user);
        return budget;
    }

}
