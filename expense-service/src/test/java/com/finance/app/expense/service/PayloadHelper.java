package com.finance.app.expense.service;

import com.finance.app.expense.service.dto.ExpenseDto;
import com.finance.app.expense.service.dto.client.BudgetDto;
import com.finance.app.expense.service.dto.client.UserDto;
import com.finance.app.expense.service.entity.Expense;

import java.time.LocalDateTime;

public class PayloadHelper {
    public static ExpenseDto mockExpenseDto() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setAmount(1000.00);
        expenseDto.setCategory("Groceries");
        expenseDto.setDescription("Purchase made towards groceries");
        expenseDto.setFkUserId(101l);
        //expenseDto.setTimestamp(LocalDateTime.now());
        return expenseDto;
    }

    public static Expense mockExpense(long expenseId) {
        Expense expense = new Expense();
        expense.setPkExpenseId(expenseId);
        expense.setFkUserId(101l);
        expense.setAmount(1000.00);
        expense.setCategory("Groceries");
        expense.setDescription("Purchase made towards groceries");
        return expense;
    }

    public static Expense mockExpense() {
        Expense expense = new Expense();
        expense.setFkUserId(101l);
        expense.setAmount(1000.00);
        expense.setCategory("Groceries");
        expense.setDescription("Purchase made towards groceries");
        return expense;
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

    public static BudgetDto mockBudgetDto() {
        BudgetDto budgetDto = new BudgetDto();
        budgetDto.setAmount(10000.00);
        budgetDto.setCategory("Groceries");
        budgetDto.setFkUserId(101l);
        return budgetDto;
    }
}
