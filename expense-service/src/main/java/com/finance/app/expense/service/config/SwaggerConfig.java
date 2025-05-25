package com.finance.app.expense.service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIConfiguration() {
        return new OpenAPI().info(new Info().title("Finance Management Application").version("1.0")
                .description("This service responsible for tracking user expenses")
                .termsOfService("FMA T&C")
                .license(new License().name("FMA Licence LLC"))
                .contact(new Contact().name("Yashwanth Kumar HT").email("ht.yashwanthkumar@gmail.com")));
    }
}
