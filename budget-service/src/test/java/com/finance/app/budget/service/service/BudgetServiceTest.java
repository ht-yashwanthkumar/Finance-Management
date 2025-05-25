package com.finance.app.budget.service.service;

import com.finance.app.budget.service.PayloadHelper;
import com.finance.app.budget.service.client.UserClient;
import com.finance.app.budget.service.dto.BudgetDto;
import com.finance.app.budget.service.dto.ResponseBody;
import com.finance.app.budget.service.dto.client.UserDto;
import com.finance.app.budget.service.exception.dto.BudgetNotFoundException;
import com.finance.app.budget.service.exception.dto.UserNotFoundException;
import com.finance.app.budget.service.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BudgetServiceTest {

    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetService budgetService;

    @Mock
    private UserClient userClient;

    @Test
    void testSaveBudget_WhenUserExists() {
        mockValidUser();
        when(budgetRepository.save(Mockito.any())).thenReturn(PayloadHelper.mockBudget(101l));
        Long budgetId = budgetService.saveBudget(PayloadHelper.mockBudgetDto());
        assertNotNull(budgetId);
        assertEquals(101L, budgetId);
    }

    @Test
    void testSaveBudget_WhenUserDoesNotExist_ThrowsException() {
        when(userClient.getUser(Mockito.anyLong())).thenReturn(ResponseEntity.ok(new ResponseBody()));
        assertThrows(UserNotFoundException.class, () -> budgetService.saveBudget(PayloadHelper.mockBudgetDto()));
    }

    @Test
    void testGetBudgetsByUser_WhenUserExists() {
        mockValidUser();
        when(budgetRepository.findByFkUserId(Mockito.anyLong())).thenReturn(List.of(PayloadHelper.mockBudget(101l), PayloadHelper.mockBudget(102l)));
        List<BudgetDto> result = budgetService.getBudgetsByUser(101l);

        assertEquals(2, result.size());
        assertEquals(101l, result.get(0).getPkBudgetId());
        assertEquals(102l, result.get(1).getPkBudgetId());
    }

    @Test
    void testGetBudgetByUserAndCategory_WhenExists() {
        mockValidUser();
        when(budgetRepository.findByFkUserIdAndCategory(Mockito.anyLong(), Mockito.anyString())).thenReturn(Optional.of(PayloadHelper.mockBudget(101l)));

        Optional<BudgetDto> result = budgetService.getBudgetByUserAndCategory(101l, "Groceries");
        assertTrue(result.isPresent());
        assertEquals("Groceries", result.get().getCategory());
        assertEquals(10000.00, result.get().getAmount());
    }

    @Test
    void testGetBudgetByUserAndCategory_WhenNotExists() {
        mockValidUser();
        when(budgetRepository.findByFkUserIdAndCategory(Mockito.anyLong(), Mockito.anyString())).thenReturn(Optional.empty());
        Optional<BudgetDto> result = budgetService.getBudgetByUserAndCategory(101l, "Groceries");
        assertTrue(result.isEmpty());
    }

    @Test
    void testDeleteBudgetByUserAndCategory_WhenBudgetNotExists() {
        mockValidUser();
        when(budgetRepository.existsByFkUserIdAndCategory(Mockito.anyLong(), Mockito.any()))
                .thenReturn(false);
        assertThrows(BudgetNotFoundException.class, () -> budgetService.deleteBudgetByUserAndCategory(101l, "Groceries"));
    }

    @Test
    void testDeleteBudgetByUserAndCategory_WhenBudgetExists() {
        mockValidUser();
        when(budgetRepository.existsByFkUserIdAndCategory(Mockito.anyLong(), Mockito.any()))
                .thenReturn(true);
        when(budgetRepository.save(Mockito.any())).thenReturn(PayloadHelper.mockBudget(101l));
        Long budgetId = budgetService.saveBudget(PayloadHelper.mockBudgetDto());
        assertNotNull(budgetId);
        assertEquals(101L, budgetId);
    }

    @Test
    void testUpdateBudgetByUserAndCategory_WhenBudgetNotExists() {
        mockValidUser();
        when(budgetRepository.existsByFkUserIdAndCategory(Mockito.anyLong(), Mockito.any()))
                .thenReturn(false);
        assertThrows(BudgetNotFoundException.class, () -> budgetService.updateBudget(101l, "Groceries", PayloadHelper.mockBudgetDto_Update()));
    }

    @Test
    void testUpdateBudgetByUserAndCategory_WhenBudgetExists() {
        mockValidUser();
        when(budgetRepository.findByFkUserIdAndCategory(Mockito.anyLong(), Mockito.any()))
                .thenReturn(Optional.of(PayloadHelper.mockBudget(101l)));
        when(budgetRepository.save(Mockito.any())).thenReturn(PayloadHelper.mockBudget(101l));
        Long budgetId = budgetService.updateBudget(101l, "Groceries", PayloadHelper.mockBudgetDto_Update());
        assertNotNull(budgetId);
        assertEquals(101L, budgetId);
    }

    private void mockValidUser() {
        ResponseBody<UserDto> responseBody = new ResponseBody<>();
        responseBody.setData(PayloadHelper.mockUserDto()); // simulate user exists
        ResponseEntity<ResponseBody<UserDto>> responseEntity = ResponseEntity.ok(responseBody);
        when(userClient.getUser(Mockito.anyLong())).thenReturn(responseEntity);
    }
}
