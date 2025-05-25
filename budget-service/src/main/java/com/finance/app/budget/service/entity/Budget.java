package com.finance.app.budget.service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "budget")
public class Budget {
    /**
     * The pk budget id.
     */
    @Column(name = "pk_budget_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pkBudgetId;
    /**
     * The pk budget id.
     */
    @Column(name = "category")
    private String category;
    /**
     * The pk budget id.
     */
    @Column(name = "amount")
    private Double amount;
    /**
     * The pk budget id.
     */
    @Column(name = "fk_user_id")
    private Long fkUserId;

    public Long getPkBudgetId() {
        return pkBudgetId;
    }

    public void setPkBudgetId(Long pkBudgetId) {
        this.pkBudgetId = pkBudgetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }
}
