package com.finance.app.budget.service.client;

import com.finance.app.budget.service.config.FeignConfig;
import com.finance.app.budget.service.dto.ResponseBody;
import com.finance.app.budget.service.dto.client.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = FeignConfig.class)
public interface UserClient {

    @GetMapping("/user/{userId}")
    ResponseEntity<ResponseBody<UserDto>> getUser(@PathVariable("userId") Long userId);
}
