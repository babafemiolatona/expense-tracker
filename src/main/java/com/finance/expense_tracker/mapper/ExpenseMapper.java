package com.finance.expense_tracker.mapper;

import com.finance.expense_tracker.Dto.CreateExpenseDto;
import com.finance.expense_tracker.Dto.ExpenseDto;
import com.finance.expense_tracker.models.Category;
import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;

public class ExpenseMapper {

    public static ExpenseDto toDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setDate(expense.getDate());
        
        if (expense.getCategory() != null) {
            dto.setCategoryId(expense.getCategory().getId());
        }

        if (expense.getUser() != null) {
            dto.setUserId(expense.getUser().getId());
        }

        return dto;
    }

    public static Expense fromDto(CreateExpenseDto dto, User user, Category category) {
        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setDate(dto.getDate());
        expense.setUser(user);
        expense.setCategory(category);

        return expense;
    }

}
