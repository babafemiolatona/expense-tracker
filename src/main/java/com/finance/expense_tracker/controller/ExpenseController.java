package com.finance.expense_tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finance.expense_tracker.Dto.CreateExpenseDto;
import com.finance.expense_tracker.Dto.ExpenseDto;
import com.finance.expense_tracker.mapper.ExpenseMapper;
import com.finance.expense_tracker.models.Expense;
import com.finance.expense_tracker.models.User;
import com.finance.expense_tracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> getAllExpenses(@AuthenticationPrincipal User user) {
        List<ExpenseDto> dtos = expenseService.getAllExpenses(user)
            .stream()
            .map(ExpenseMapper::toDto)
            .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseDto> getExpenseById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Expense expense = expenseService.getExpenseById(id, user);

        if (expense == null || !expense.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        ExpenseDto dto = ExpenseMapper.toDto(expense);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ExpenseDto> addExpense(@RequestBody CreateExpenseDto expenseDto,
                                                 @AuthenticationPrincipal User user) {
        Expense createdExpense = expenseService.addExpense(expenseDto, user);
        ExpenseDto responseDto = ExpenseMapper.toDto(createdExpense);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(
            @PathVariable Long id,
            @RequestBody CreateExpenseDto updatedDto,
            @AuthenticationPrincipal User user) {
        Expense expense = expenseService.updateExpense(id, updatedDto, user);
        ExpenseDto responseDto = ExpenseMapper.toDto(expense);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, @AuthenticationPrincipal User user) {
        expenseService.deleteExpense(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
