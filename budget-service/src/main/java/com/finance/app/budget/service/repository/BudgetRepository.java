package com.finance.app.budget.service.repository;

import com.finance.app.budget.service.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByFkUserId(Long fkUserId);

    Optional<Budget> findByFkUserIdAndCategory(Long userId, String category);

    boolean existsByFkUserIdAndCategory(Long userId, String category);

    void deleteByFkUserIdAndCategory(Long userId, String category);
}
