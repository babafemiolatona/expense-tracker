package com.finance.expense_tracker.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class IncomeDto {

    private Long id;
    private String source;
    private Double amount;
    private LocalDate date;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
