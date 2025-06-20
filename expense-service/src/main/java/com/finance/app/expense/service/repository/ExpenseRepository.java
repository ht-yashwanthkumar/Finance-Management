package com.finance.app.expense.service.repository;

import com.finance.app.expense.service.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByFkUserId(Long userId);
}
