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

import com.finance.expense_tracker.Dto.BudgetDto;
import com.finance.expense_tracker.Dto.CreateBudgetDto;
import com.finance.expense_tracker.mapper.BudgetMapper;
import com.finance.expense_tracker.models.Budget;
import com.finance.expense_tracker.models.User;
import com.finance.expense_tracker.service.BudgetService;


@RestController
@RequestMapping("/api/v1/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    public ResponseEntity<List<BudgetDto>> getAllBudgets(@AuthenticationPrincipal User user) {
        List<BudgetDto> dtos = budgetService.getAllBudgets(user)
            .stream()
            .map(BudgetMapper::toDto)
            .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BudgetDto> getBudgetById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Budget budget = budgetService.getBudgetById(id, user);

        if (budget == null || !budget.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        BudgetDto dto = BudgetMapper.toDto(budget);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<BudgetDto> addBudget(@RequestBody CreateBudgetDto budgetDto,
                                               @AuthenticationPrincipal User user) {
        Budget createdBudget = budgetService.addBudget(budgetDto, user);
        BudgetDto responseDto = BudgetMapper.toDto(createdBudget);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BudgetDto> updateBudget(
            @PathVariable Long id,
            @RequestBody CreateBudgetDto budgetDto,
            @AuthenticationPrincipal User user) {
        Budget updatedBudget = budgetService.updateBudget(id, budgetDto, user);
        BudgetDto responseDto = BudgetMapper.toDto(updatedBudget);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id, @AuthenticationPrincipal User user) {
        budgetService.deleteBudget(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
