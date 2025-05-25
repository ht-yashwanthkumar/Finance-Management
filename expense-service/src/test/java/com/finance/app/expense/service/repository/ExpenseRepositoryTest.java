package com.finance.app.expense.service.repository;

import com.finance.app.expense.service.PayloadHelper;
import com.finance.app.expense.service.entity.Expense;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class ExpenseRepositoryTest {

    @Autowired
    ExpenseRepository expenseRepository;

    @Test
    void testSaveExpense() {
        Expense expense = expenseRepository.save(PayloadHelper.mockExpense());
        assertNotNull(expense);
        assertEquals("Groceries", expense.getCategory());
    }

    @Test
    void findByFkUserId_WhenExists() {
        Expense expense = expenseRepository.save(PayloadHelper.mockExpense());
        List<Expense> expenses = expenseRepository.findByFkUserId(expense.getFkUserId());
        assertFalse(expenses.isEmpty());
        assertEquals("Groceries", expenses.get(0).getCategory());
    }

    @Test
    void testFindByFkUserId_WhenNotExists() {
        List<Expense> expenses = expenseRepository.findByFkUserId(999L);
        assertTrue(expenses.isEmpty());
    }
}
