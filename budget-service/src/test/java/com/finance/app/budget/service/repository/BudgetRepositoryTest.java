package com.finance.app.budget.service.repository;

import com.finance.app.budget.service.PayloadHelper;
import com.finance.app.budget.service.entity.Budget;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class BudgetRepositoryTest {

    @Autowired
    BudgetRepository budgetRepository;

    @Test
    void testSaveBudget() {
        Budget budget = budgetRepository.save(PayloadHelper.mockBudget());
        assertNotNull(budget);
        assertEquals("Groceries", budget.getCategory());
    }

    @Test
    void findByFkUserId_WhenExists() {
        Budget budget = budgetRepository.save(PayloadHelper.mockBudget());
        List<Budget> budgets = budgetRepository.findByFkUserId(budget.getFkUserId());
        assertFalse(budgets.isEmpty());
        assertEquals("Groceries", budgets.get(0).getCategory());
    }

    @Test
    void testFindByFkUserId_WhenNotExists() {
        List<Budget> budgets = budgetRepository.findByFkUserId(999L);
        assertTrue(budgets.isEmpty());
    }

    @Test
    void findByFkUserIdAndCategory_WhenExists() {
        Budget budget = budgetRepository.save(PayloadHelper.mockBudget());
        Optional<Budget> optionalBudget = budgetRepository.findByFkUserIdAndCategory(budget.getFkUserId(), "Groceries");
        assertFalse(optionalBudget.isEmpty());
        assertEquals("Groceries", optionalBudget.get().getCategory());
    }

    @Test
    void testFindByFkUserIdAndCategory_WhenNotExists() {
        Optional<Budget> optionalBudget = budgetRepository.findByFkUserIdAndCategory(999L, "Groceries");
        assertTrue(optionalBudget.isEmpty());
    }

    @Test
    void testExistsByFkUserIdAndCategory_WhenExists() {
        Budget budget = budgetRepository.save(PayloadHelper.mockBudget());
        boolean exists = budgetRepository.existsByFkUserIdAndCategory(budget.getFkUserId(), budget.getCategory());
        assertTrue(exists);
    }

    @Test
    void testExistsByFkUserIdAndCategory_WhenNotExists() {
        boolean exists = budgetRepository.existsByFkUserIdAndCategory(101l, "Groceries");
        assertFalse(exists);
    }
}
