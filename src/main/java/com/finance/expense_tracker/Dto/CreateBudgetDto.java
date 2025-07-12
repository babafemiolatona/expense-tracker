package com.finance.expense_tracker.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateBudgetDto {

    private Double limitAmount;
    private String category;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
