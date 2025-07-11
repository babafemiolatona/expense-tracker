package com.finance.expense_tracker.mapper;

import com.finance.expense_tracker.Dto.CreateExpenseDto;
import com.finance.expense_tracker.Dto.ExpenseDto;
import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;

public class ExpenseMapper {

    public static ExpenseDto toDto(Expense expense) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(expense.getId());
        dto.setDescription(expense.getDescription());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setDate(expense.getDate());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());

        return dto;
    }

    public static Expense fromDto(CreateExpenseDto dto, User user) {
        Expense expense = new Expense();
        expense.setDescription(dto.getDescription());
        expense.setAmount(dto.getAmount());
        expense.setUser(user);
        expense.setCategory(dto.getCategory());

        return expense;
    }

}
