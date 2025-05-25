package com.finance.app.budget.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.budget.service.PayloadHelper;
import com.finance.app.budget.service.service.BudgetService;
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
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BudgetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private BudgetController budgetController;

    @Mock
    private BudgetService budgetService;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(budgetController).build();
    }

    @Test
    public void testGetBudgetsByUser() throws Exception {
        Mockito.when(budgetService.getBudgetsByUser(Mockito.anyLong())).thenReturn(List.of(PayloadHelper.mockBudgetDto()));
        mockMvc.perform(get("/budget/user/101")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBudgetByUserAndCategory() throws Exception {
        Mockito.when(budgetService.getBudgetByUserAndCategory(Mockito.anyLong(), Mockito.anyString())).thenReturn(Optional.of(PayloadHelper.mockBudgetDto()));
        mockMvc.perform(get("/budget/user/101/category/Groceries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSaveBudget() throws Exception {
        String content = new ObjectMapper().writeValueAsString(PayloadHelper.mockBudgetDto());
        mockMvc.perform(post("/budget")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(budgetService, Mockito.times(1)).saveBudget(Mockito.any());
    }

    @Test
    public void testUpdateBudget() throws Exception {
        String content = new ObjectMapper().writeValueAsString(PayloadHelper.mockBudgetDto_Update());
        mockMvc.perform(put("/budget/user/101/category/Groceries")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(budgetService, Mockito.times(1)).updateBudget(Mockito.any(), Mockito.any(), Mockito.any());
    }

    @Test
    public void deleteBudgetByCategory() throws Exception {
        mockMvc.perform(delete("/budget/user/101/category/Groceries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(budgetService, Mockito.times(1)).deleteBudgetByUserAndCategory(Mockito.any(), Mockito.any());
    }
}
