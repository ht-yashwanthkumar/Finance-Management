package com.finance.app.budget.service;

import com.finance.app.budget.service.dto.BudgetDto;
import com.finance.app.budget.service.dto.client.UserDto;
import com.finance.app.budget.service.entity.Budget;

public class PayloadHelper {
    public static BudgetDto mockBudgetDto() {
        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setAmount(10000.00);
        budgetDto.setCategory("Groceries");
        budgetDto.setFkUserId(101l);
        return budgetDto;
    }

    public static Budget mockBudget(long budgetId) {
        Budget budget = new Budget();
        budget.setPkBudgetId(budgetId);
        budget.setCategory("Groceries");
        budget.setAmount(10000.00);
        budget.setFkUserId(101l);
        return budget;
    }

    public static Budget mockBudget() {
        Budget budget = new Budget();
        budget.setCategory("Groceries");
        budget.setAmount(10000.00);
        budget.setFkUserId(101l);
        return budget;
    }

    public static UserDto mockUserDto() {
        UserDto userDto = new UserDto();
        userDto.setTitle("MR");
        userDto.setFirstName("Chris");
        userDto.setLastName("Hemsworth");
        userDto.setEmail("chris.hemsworth@mail.com");
        userDto.setPhoneNumber("+971582279099");
        return userDto;
    }

    public static BudgetDto mockBudgetDto_Update() {
        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setAmount(10000.00);
        return budgetDto;
    }
}
