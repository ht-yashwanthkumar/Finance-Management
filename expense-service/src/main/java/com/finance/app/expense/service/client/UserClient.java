package com.finance.app.expense.service.client;

import com.finance.app.expense.service.config.UserClientConfig;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.dto.client.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", configuration = UserClientConfig.class)
public interface UserClient {

    @GetMapping("/user/{userId}")
    ResponseEntity<ResponseBody<UserDto>> getUser(@PathVariable("userId") Long userId);
}
