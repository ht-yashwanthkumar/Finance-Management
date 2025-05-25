package com.finance.app.budget.service.dto;

public class BudgetDto {
    private Long pkBudgetId;

    //@NotBlank(message = "Budget Category must not be blank")
    private String category;

    //@NotBlank(message = "Budget amount must not be blank")
    private Double amount;

    //@NotBlank(message = "User Id must not be blank")
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
