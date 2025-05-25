package com.finance.app.expense.service.dto;

import java.time.LocalDateTime;

public class ExpenseDto {
    private Long pkExpenseId;
    private Long fkUserId;
    private String category;
    private String description;
    private Double amount;
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
