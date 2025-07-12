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

import com.finance.expense_tracker.Dto.CreateIncomeDto;
import com.finance.expense_tracker.Dto.IncomeDto;
import com.finance.expense_tracker.mapper.IncomeMapper;
import com.finance.expense_tracker.models.Income;
import com.finance.expense_tracker.models.User;
import com.finance.expense_tracker.service.IncomeService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/income")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @GetMapping
    public ResponseEntity<List<IncomeDto>> getAllIncome(@AuthenticationPrincipal User user) {
        List<IncomeDto> incomeDtos = incomeService.getAllIncomes(user)
            .stream()
            .map(IncomeMapper::toDto)
            .toList();
        return ResponseEntity.ok(incomeDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncomeDto> getIncomeById(@PathVariable Long id, @AuthenticationPrincipal User user) {
        Income income = incomeService.getIncomeById(id, user);

        if (income == null || !income.getUser().getId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        IncomeDto incomeDto = IncomeMapper.toDto(income);
        return ResponseEntity.ok(incomeDto);
    }

    @PostMapping
    public ResponseEntity<IncomeDto> addIncome(@RequestBody CreateIncomeDto incomeDto,
                                               @AuthenticationPrincipal User user) {
        Income createdIncome = incomeService.addIncome(incomeDto, user);
        IncomeDto responseDto = IncomeMapper.toDto(createdIncome);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncomeDto> updateIncome(
            @PathVariable Long id,
            @RequestBody CreateIncomeDto incomeDto,
            @AuthenticationPrincipal User user) {
        Income updatedIncome = incomeService.updateIncome(id, incomeDto, user);
        if (updatedIncome == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        IncomeDto responseDto = IncomeMapper.toDto(updatedIncome);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable Long id, @AuthenticationPrincipal User user) {
        incomeService.deleteIncome(id, user);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
