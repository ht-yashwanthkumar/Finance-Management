package com.finance.app.expense.service.config;

import com.finance.app.expense.service.exception.decoder.UserClientErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(value = "userClientConfig")
public class UserClientConfig {

    @Bean("budgetClientErrorDecoder")
    public ErrorDecoder errorDecoder() {
        return new UserClientErrorDecoder();
    }
}
