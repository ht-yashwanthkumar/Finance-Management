package com.finance.app.expense.service.client;

import com.finance.app.expense.service.config.BudgetClientConfig;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.dto.client.BudgetDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "budget-service", configuration = BudgetClientConfig.class)
public interface BudgetClient {
    @GetMapping("budget/user/{userId}/category/{category}")
    ResponseEntity<ResponseBody<BudgetDto>> getBudgetByUserAndCategory(@PathVariable("userId") Long userId, @PathVariable("category") String category);

    @PutMapping("budget/user/{userId}/category/{category}")
    ResponseEntity<ResponseBody<Long>> updateBudget(@PathVariable("userId") Long userId, @PathVariable("category") String category, @RequestBody BudgetDto budgetDto);
}
