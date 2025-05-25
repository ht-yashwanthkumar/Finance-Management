package com.finance.app.expense.service.client;

import com.finance.app.expense.service.config.UserClientConfig;
import com.finance.app.expense.service.dto.ResponseBody;
import com.finance.app.expense.service.dto.client.NotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", configuration = UserClientConfig.class)
public interface NotificationClient {

    @PostMapping("notification/send-notification")
    ResponseEntity<ResponseBody<String>> sendNotification(@RequestBody NotificationDto notificationDto);
}
