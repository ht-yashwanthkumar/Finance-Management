package com.finance.app.budget.service.service;

import com.finance.app.budget.service.client.UserClient;
import com.finance.app.budget.service.dto.BudgetDto;
import com.finance.app.budget.service.dto.ResponseBody;
import com.finance.app.budget.service.dto.client.UserDto;
import com.finance.app.budget.service.entity.Budget;
import com.finance.app.budget.service.exception.dto.BudgetNotFoundException;
import com.finance.app.budget.service.exception.dto.UserNotFoundException;
import com.finance.app.budget.service.repository.BudgetRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BudgetService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetService.class);

    @Autowired
    BudgetRepository budgetRepository;

    @Autowired
    UserClient userClient;

    @Transactional
    public Long saveBudget(BudgetDto budgetDto) {
        LOGGER.debug("Entered into saveBudget method");
        validateUser(budgetDto.getFkUserId());

        Budget budget = new Budget();
        budget.setAmount(budgetDto.getAmount());
        budget.setCategory(budgetDto.getCategory());
        budget.setFkUserId(budgetDto.getFkUserId());
        return budgetRepository.save(budget).getPkBudgetId();
    }

    @Transactional
    public Long updateBudget(Long userId, String category, BudgetDto budgetDto) {
        LOGGER.debug("Entered into updateBudget method");
        validateUser(userId);

        Budget budget = budgetRepository.findByFkUserIdAndCategory(userId, category)
                .orElseThrow(() -> new BudgetNotFoundException(
                        HttpStatus.NOT_FOUND, "Budget not found for the user with id: " + userId + " and category: " + category));

        budget.setAmount(budgetDto.getAmount());
        return budgetRepository.save(budget).getPkBudgetId();
    }


    public List<BudgetDto> getBudgetsByUser(Long fkUserId) {
        LOGGER.debug("Entered into getBudgetsByUser method with userId: {}", fkUserId);
        validateUser(fkUserId);
        return budgetRepository.findByFkUserId(fkUserId).stream().map(this::copyBeans).collect(Collectors.toList());
    }

    public Optional<BudgetDto> getBudgetByUserAndCategory(Long userId, String category) {
        LOGGER.debug("Entered into getBudgetByUserAndCategory method with userId: {} and category: {}", userId, category);
        validateUser(userId);
        return budgetRepository.findByFkUserIdAndCategory(userId, category).map(this::copyBeans);
    }

    @Transactional
    public void deleteBudgetByUserAndCategory(Long userId, String category) {
        LOGGER.debug("Entered into deleteBudgetByUserAndCategory method with userId: {} and category: {}", userId, category);
        validateUser(userId);
        if (!budgetRepository.existsByFkUserIdAndCategory(userId, category)) {
            throw new BudgetNotFoundException(
                    HttpStatus.NOT_FOUND, "Budget not found for the user with id: " + userId + " and category: " + category);
        }
        budgetRepository.deleteByFkUserIdAndCategory(userId, category);
    }

    private void validateUser(Long userId) {
        ResponseEntity<ResponseBody<UserDto>> useResponse = userClient.getUser(userId);
        if (isInvalidResponse(useResponse)) {
            throw new UserNotFoundException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
    }

    private BudgetDto copyBeans(Budget budget) {
        return new ModelMapper().map(budget, BudgetDto.class);
    }

    private boolean isInvalidResponse(ResponseEntity<ResponseBody<UserDto>> response) {
        return response == null
                || !response.getStatusCode().is2xxSuccessful()
                || response.getBody() == null
                || response.getBody().getData() == null;
    }

}
