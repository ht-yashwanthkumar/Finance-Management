package com.finance.app.expense.service.service;

import com.finance.app.expense.service.PayloadHelper;
import com.finance.app.expense.service.client.BudgetClient;
import com.finance.app.expense.service.client.NotificationClient;
import com.finance.app.expense.service.client.UserClient;
import com.finance.app.expense.service.dto.ExpenseDto;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.dto.client.BudgetDto;
import com.finance.app.expense.service.dto.client.NotificationDto;
import com.finance.app.expense.service.dto.client.UserDto;
import com.finance.app.expense.service.exception.dto.BudgetExceededException;
import com.finance.app.expense.service.exception.dto.UserBudgetNotFoundException;
import com.finance.app.expense.service.exception.dto.UserNotFoundException;
import com.finance.app.expense.service.repository.ExpenseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {

    @InjectMocks
    private ExpenseService expenseService;

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private BudgetClient budgetClient;

    @Mock
    private NotificationClient notificationClient;

    @Test
    void testGetUserExpenses_WhenUserExists() {
        mockValidUser();
        when(expenseRepository.findByFkUserId(Mockito.anyLong())).thenReturn(List.of(PayloadHelper.mockExpense(101l), PayloadHelper.mockExpense(102l)));
        List<ExpenseDto> expenses = expenseService.getUserExpenses(101l);
        assertEquals(2, expenses.size());
        assertEquals(101l, expenses.get(0).getPkExpenseId());
        assertEquals(102l, expenses.get(1).getPkExpenseId());
    }

    @Test
    void testGetUserExpenses_WhenUserDoesNotExist_ThrowsException() {
        when(userClient.getUser(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new ResponseBody()));
        assertThrows(UserNotFoundException.class, () -> expenseService.getUserExpenses(101l));
    }

    @Test
    void testAddUserExpense_WhenUserNotFound_ThrowsException() {
        when(userClient.getUser(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new ResponseBody()));
        assertThrows(UserNotFoundException.class, () -> expenseService.addUserExpense(PayloadHelper.mockExpenseDto()));
    }

    @Test
    void testAddUserExpense_WhenBudgetNotFound_ThrowsException() {
        mockValidUser();
        when(budgetClient.getBudgetByUserAndCategory(Mockito.anyLong(), Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(new ResponseBody())); // No budget data
        assertThrows(UserBudgetNotFoundException.class, () -> expenseService.addUserExpense(PayloadHelper.mockExpenseDto()));
    }

    @Test
    void testAddUserExpense_WhenBudgetExceeded_ThrowsException() {
        ExpenseDto expenseDto = PayloadHelper.mockExpenseDto();
        mockValidUser();
        mockValidUnderBudget();
        assertThrows(BudgetExceededException.class, () -> expenseService.addUserExpense(expenseDto));
    }

    @Test
    void testAddUserExpense_WhenUnderBudget() {
        mockValidUser();
        mockValidBudget();
        when(expenseRepository.save(Mockito.any())).thenReturn(PayloadHelper.mockExpense(101l));
        Long expenseId = expenseService.addUserExpense(PayloadHelper.mockExpenseDto());
        assertEquals(101L, expenseId);
    }

    private void mockValidUser() {
        ResponseBody<UserDto> responseBody = new ResponseBody<>();
        responseBody.setData(PayloadHelper.mockUserDto()); // simulate user exists
        ResponseEntity<ResponseBody<UserDto>> responseEntity = ResponseEntity.ok(responseBody);
        when(userClient.getUser(Mockito.anyLong())).thenReturn(responseEntity);
    }

    private void mockValidUnderBudget() {
        ResponseBody<BudgetDto> responseBody = new ResponseBody<>();
        BudgetDto budgetDto = PayloadHelper.mockBudgetDto();
        budgetDto.setAmount(100.00);
        responseBody.setData(budgetDto); // simulate user exists
        ResponseEntity<ResponseBody<BudgetDto>> responseEntity = ResponseEntity.ok(responseBody);
        when(budgetClient.getBudgetByUserAndCategory(Mockito.anyLong(), Mockito.anyString())).thenReturn(responseEntity);
    }

    private void mockValidBudget() {
        ResponseBody<BudgetDto> responseBody = new ResponseBody<>();
        responseBody.setData(PayloadHelper.mockBudgetDto()); // simulate user exists
        ResponseEntity<ResponseBody<BudgetDto>> responseEntity = ResponseEntity.ok(responseBody);
        when(budgetClient.getBudgetByUserAndCategory(Mockito.anyLong(), Mockito.anyString())).thenReturn(responseEntity);
    }

}
