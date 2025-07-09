package com.finance.expense_tracker.Dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CreateExpenseDto {

    private String description;
    private Double amount;
    private LocalDateTime date;
    private String category;

}
