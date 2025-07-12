package com.finance.expense_tracker.Dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateIncomeDto {

    private String source;
    private Double amount;
    private LocalDate date;

}
