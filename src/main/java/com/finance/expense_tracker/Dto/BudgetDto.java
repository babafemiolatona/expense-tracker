package com.finance.expense_tracker.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class BudgetDto {

    private Long id;
    private String category;
    private Double limitAmount;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
