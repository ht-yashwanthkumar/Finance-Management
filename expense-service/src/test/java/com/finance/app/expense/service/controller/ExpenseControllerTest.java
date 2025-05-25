package com.finance.app.expense.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.expense.service.PayloadHelper;
import com.finance.app.expense.service.client.UserClient;
import com.finance.app.expense.service.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class ExpenseControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ExpenseService expenseService;

    @InjectMocks
    private ExpenseController expenseController;

    @Mock
    private UserClient userClient;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(expenseController).build();
    }

    @Test
    public void testGetExpensesByUser() throws Exception {
        Mockito.when(expenseService.getUserExpenses(Mockito.anyLong())).thenReturn(List.of(PayloadHelper.mockExpenseDto()));
        mockMvc.perform(get("/expense/user/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveExpense() throws Exception {
        String content = new ObjectMapper().writeValueAsString(PayloadHelper.mockExpenseDto());
        mockMvc.perform(post("/expense")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
