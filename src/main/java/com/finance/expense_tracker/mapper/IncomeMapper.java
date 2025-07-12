package com.finance.expense_tracker.mapper;

import com.finance.expense_tracker.Dto.CreateIncomeDto;
import com.finance.expense_tracker.Dto.IncomeDto;
import com.finance.expense_tracker.models.Income;
import com.finance.expense_tracker.models.User;

public class IncomeMapper {

    public static IncomeDto toDto(Income income) {
        IncomeDto dto = new IncomeDto();
        dto.setId(income.getId());
        dto.setSource(income.getSource());
        dto.setAmount(income.getAmount());
        dto.setDate(income.getDate());
        dto.setCreatedAt(income.getCreatedAt());
        dto.setUpdatedAt(income.getUpdatedAt());
        return dto;
    }

    public static Income fromDto(CreateIncomeDto createIncomeDto, User user) {
        Income income = new Income();
        income.setSource(createIncomeDto.getSource());
        income.setAmount(createIncomeDto.getAmount());
        income.setDate(createIncomeDto.getDate());
        income.setUser(user);
        return income;
    }

}
