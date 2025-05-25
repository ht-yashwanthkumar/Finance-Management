package com.finance.app.budget.service.controller;

import com.finance.app.budget.service.dto.BudgetDto;
import com.finance.app.budget.service.dto.ResponseBody;
import com.finance.app.budget.service.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/budget")
public class BudgetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    private BudgetService budgetService;

    @Operation(summary = "Get User Budget Details")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget Details", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Budget not found for the request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while fetching Budget details for the users")})

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseBody<List<BudgetDto>>> getBudgetsByUser(@PathVariable("userId") Long userId) {
        LOGGER.debug("Entered into getBudgetsByUser method");
        List<BudgetDto> budgetDtos = budgetService.getBudgetsByUser(userId);
        return !budgetDtos.isEmpty() ? new ResponseEntity<ResponseBody<List<BudgetDto>>>(ResponseBody.of("Budgets retrieved Successfully", budgetDtos), HttpStatus.OK) : new ResponseEntity<ResponseBody<List<BudgetDto>>>(ResponseBody.of("No Budgets found for the user with id: " + userId, Collections.emptyList()), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Save Budget Details")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget created successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while creating budget")})
    @PostMapping
    public ResponseEntity<ResponseBody<Long>> saveBudget(/*@Valid*/ @RequestBody BudgetDto budgetDto) {
        LOGGER.debug("Entered into saveBudget method");
        return new ResponseEntity<ResponseBody<Long>>(ResponseBody.of("Budget created Successfully", budgetService.saveBudget(budgetDto)), HttpStatus.OK);
    }

    @Operation(summary = "Get User Budget Details By Category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget Details", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Budget not found for the request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while fetching Budget details for the users")})
    @GetMapping("/user/{userId}/category/{category}")
    public ResponseEntity<ResponseBody<BudgetDto>> getBudgetByUserAndCategory(@PathVariable("userId") Long userId, @PathVariable("category") String category) {
        LOGGER.debug("Entered into getBudgetByUserAndCategory method");
        Optional<BudgetDto> budgetByUserAndCategoryOptional = budgetService.getBudgetByUserAndCategory(userId, category);
        return budgetByUserAndCategoryOptional.map(budgetDto -> new ResponseEntity<>(ResponseBody.of("Budgets retrieved Successfully", budgetDto), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(ResponseBody.of("Budget not found for the user with id: " + userId + " and category: " + category, null), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Delete User Budget By Category")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Delete User Budget By Category", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDto.class))}),
            @ApiResponse(responseCode = "404", description = "Budget not found for the request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while deleting Budget details for the users")})
    @DeleteMapping("/user/{userId}/category/{category}")
    public ResponseEntity<ResponseBody<Void>> deleteBudgetByUserAndCategory(@PathVariable("userId") Long userId, @PathVariable("category") String category) {
        LOGGER.debug("Entered into deleteBudgetByUserAndCategory method");
        budgetService.deleteBudgetByUserAndCategory(userId, category);
        return new ResponseEntity<>(ResponseBody.of("Budget deleted Successfully"), HttpStatus.OK);
    }

    @Operation(summary = "Update User Budget ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Budget Updated successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BudgetDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while updating budget")})
    @PutMapping("/user/{userId}/category/{category}")
    public ResponseEntity<ResponseBody<Long>> updateBudget(/*@Valid*/ @PathVariable("userId") Long userId, @PathVariable("category") String category, @RequestBody BudgetDto budgetDto) {
        LOGGER.debug("Entered into updateBudget method");
        return new ResponseEntity<ResponseBody<Long>>(ResponseBody.of("Budget updated Successfully", budgetService.updateBudget(userId, category, budgetDto)), HttpStatus.OK);
    }
}
