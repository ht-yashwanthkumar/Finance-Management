package com.finance.app.expense.service.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "expense")
public class Expense {

    /**
     * The pk expense id.
     */
    @Column(name = "pk_expense_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pkExpenseId;

    /**
     * The fk user id.
     */
    @Column(name = "fk_user_id")
    private Long fkUserId;

    /**
     * The category.
     */
    @Column(name = "category")
    private String category;
    /**
     * The description.
     */
    @Column(name = "description")
    private String description;

    /**
     * The amount spent.
     */
    @Column(name = "amount_spent")
    private Double amount;

    /**
     * The expense date
     */
    @Column(name = "expense_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime timestamp;

    public Long getPkExpenseId() {
        return pkExpenseId;
    }

    public void setPkExpenseId(Long pkExpenseId) {
        this.pkExpenseId = pkExpenseId;
    }

    public Long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(Long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
