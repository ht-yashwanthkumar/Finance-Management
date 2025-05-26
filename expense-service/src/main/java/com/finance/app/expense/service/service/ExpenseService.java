package com.finance.app.expense.service.service;

import com.finance.app.expense.service.client.BudgetClient;
import com.finance.app.expense.service.client.NotificationClient;
import com.finance.app.expense.service.client.UserClient;
import com.finance.app.expense.service.dto.ExpenseDto;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.dto.client.BudgetDto;
import com.finance.app.expense.service.dto.client.NotificationDto;
import com.finance.app.expense.service.dto.client.UserDto;
import com.finance.app.expense.service.entity.Expense;
import com.finance.app.expense.service.exception.dto.BudgetExceededException;
import com.finance.app.expense.service.exception.dto.UserBudgetNotFoundException;
import com.finance.app.expense.service.exception.dto.UserNotFoundException;
import com.finance.app.expense.service.repository.ExpenseRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseService.class);
    private BudgetClient budgetClient;
    private UserClient userClient;
    private NotificationClient notificationClient;
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(BudgetClient budgetClient, UserClient userClient, NotificationClient notificationClient, ExpenseRepository expenseRepository) {
        this.budgetClient = budgetClient;
        this.userClient = userClient;
        this.notificationClient = notificationClient;
        this.expenseRepository = expenseRepository;
    }

    public List<ExpenseDto> getUserExpenses(Long userId) {
        LOGGER.debug("Entered into getUserExpenses method with userId: {}", userId);
        validateUser(userId);
        return expenseRepository.findByFkUserId(userId).stream().map(this::copyBeans).collect(Collectors.toList());
    }

    @Transactional
    public Long addUserExpense(ExpenseDto expenseDto) {
        LOGGER.debug("Entered into addUserExpense method");
        validateUser(expenseDto.getFkUserId());
        BudgetDto budgetDto = getUserBudgetByCategory(expenseDto.getFkUserId(), expenseDto.getCategory());
        if (budgetDto != null && budgetDto.getAmount() - expenseDto.getAmount() >= 0) {
            long expenseId = saveExpense(expenseDto);
            budgetDto.setAmount(budgetDto.getAmount() - expenseDto.getAmount());
            budgetClient.updateBudget(expenseDto.getFkUserId(), expenseDto.getCategory(), budgetDto);
            return expenseId;
        } else {
            notificationClient.sendNotification(prepareNotificationDto(expenseDto, budgetDto));
            throw new BudgetExceededException(HttpStatus.BAD_REQUEST, "User budget exceeded for the category: " + expenseDto.getCategory());
        }
    }

    private Long saveExpense(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setAmount(expenseDto.getAmount());
        expense.setCategory(expenseDto.getCategory());
        expense.setDescription(expenseDto.getDescription());
        expense.setFkUserId(expenseDto.getFkUserId());
        expense.setTimestamp(LocalDateTime.now());
        return expenseRepository.save(expense).getPkExpenseId();
    }

    @CircuitBreaker(name = "userServiceFB", fallbackMethod = "getUserFallback")
    private void validateUser(Long userId) {
        LOGGER.debug("Entered into validateUser method for userId: {}", userId);
        ResponseEntity<ResponseBody<UserDto>> useResponse = userClient.getUser(userId);
        if (isInvalidResponse(useResponse)) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
    }

    @CircuitBreaker(name = "budgetServiceFB", fallbackMethod = "getBudgetFallback")
    private BudgetDto getUserBudgetByCategory(long userId, String category) {
        LOGGER.debug("Entered into getUserBudgetByCategory method for userId: {} and category: {}", userId, category);

        ResponseEntity<ResponseBody<BudgetDto>> response = budgetClient.getBudgetByUserAndCategory(userId, category);
        if (isInvalidResponse(response))
            throw new UserBudgetNotFoundException(HttpStatus.BAD_REQUEST, "Budget not found for the user");
        return response.getBody().getData();
    }

    public UserDto getUserFallback(Long userId, Throwable throwable) {
        LOGGER.error("Fallback method called for getUser with userId: {} due to: {}", userId, throwable.getMessage());
        throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User Not Found - Fallback triggered");
    }

    public BudgetDto getBudgetFallback(Long userId, String category, Throwable throwable) {
        LOGGER.error("Fallback method called for getUserBudgetByCategory with userId: {} and category: {} due to: {}", userId, category, throwable.getMessage());
        throw new UserBudgetNotFoundException(HttpStatus.BAD_REQUEST, "Budget Not Found - Fallback triggered");
    }

    private ExpenseDto copyBeans(Expense expense) {
        return new ModelMapper().map(expense, ExpenseDto.class);
    }

    private NotificationDto prepareNotificationDto(ExpenseDto expenseDto, BudgetDto budgetDto) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setBudgetAmount(budgetDto.getAmount());
        notificationDto.setExpenseAmount(expenseDto.getAmount());
        notificationDto.setFkUserId(expenseDto.getFkUserId());
        notificationDto.setCategory(expenseDto.getCategory());
        notificationDto.setExpenseDescription(expenseDto.getDescription());
        return notificationDto;
    }

    public <T> boolean isInvalidResponse(ResponseEntity<ResponseBody<T>> response) {
        return response == null
                || !response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null
                || response.getBody().getData() == null;
    }
}
