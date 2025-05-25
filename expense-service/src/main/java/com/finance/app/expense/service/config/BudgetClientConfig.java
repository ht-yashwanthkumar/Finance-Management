package com.finance.app.expense.service.config;

import com.finance.app.expense.service.exception.decoder.BudgetClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "budgetClientConfig")
public class BudgetClientConfig {

    @Bean("userClientErrorDecoder")
    public ErrorDecoder errorDecoder() {
        return new BudgetClientErrorDecoder();
    }
}
