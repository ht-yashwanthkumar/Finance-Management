package com.finance.app.expense.service.controller;

import com.finance.app.expense.service.dto.ExpenseDto;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.service.ExpenseService;
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

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);


    @Autowired
    private ExpenseService expenseService;

    @Operation(summary = "Get User All Expense Details ")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User expense retrieved successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User expenses not found for the request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while updating budget")})

    @GetMapping("/user/{userId}")
    public ResponseEntity<ResponseBody<List<ExpenseDto>>> getUserExpenses(@PathVariable("userId") Long userId) {
        LOGGER.info("Entered into getUserExpenses method");
        List<ExpenseDto> expenseDtos = expenseService.getUserExpenses(userId);
        return !expenseDtos.isEmpty() ? new ResponseEntity<ResponseBody<List<ExpenseDto>>>(ResponseBody.of("User expenses retrieved Successfully", expenseDtos), HttpStatus.OK) : new ResponseEntity<ResponseBody<List<ExpenseDto>>>(ResponseBody.of("No user expenses found", Collections.emptyList()), HttpStatus.NOT_FOUND);
    }

    @Operation(summary = "Save User Expenses")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "User expense saved successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ExpenseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while saving expense")})
    @PostMapping
    public ResponseEntity<ResponseBody<Long>> saveExpense(/*@Valid*/ @RequestBody ExpenseDto expenseDto) {
        LOGGER.info("Entered into saveExpense method");
        return new ResponseEntity<ResponseBody<Long>>(ResponseBody.of("User expense saved Successfully", expenseService.addUserExpense(expenseDto)), HttpStatus.OK);
    }
}
