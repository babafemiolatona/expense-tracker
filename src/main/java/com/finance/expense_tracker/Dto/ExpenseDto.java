package com.finance.expense_tracker.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExpenseDto {

    private Long id;
    private String description;
    private Double amount;
    private String category;
    private LocalDateTime date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
