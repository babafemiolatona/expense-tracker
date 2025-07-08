package com.finance.expense_tracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.finance.expense_tracker.models.Category;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
