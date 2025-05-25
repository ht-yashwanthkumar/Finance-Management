package com.finance.app.notification.service.dto;

public class NotificationDto {
    private long fkUserId;
    private String category;
    private Double budgetAmount;
    private String expenseDescription;
    private Double expenseAmount;

    public long getFkUserId() {
        return fkUserId;
    }

    public void setFkUserId(long fkUserId) {
        this.fkUserId = fkUserId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(Double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(Double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
