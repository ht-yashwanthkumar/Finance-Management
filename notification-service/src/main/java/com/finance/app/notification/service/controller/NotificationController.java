package com.finance.app.notification.service.controller;

import com.finance.app.notification.service.dto.NotificationDto;
import com.finance.app.notification.service.dto.ResponseBody;
import com.finance.app.notification.service.service.NotificationService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationService notificationService;

    @Operation(summary = "Send Notification")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Notification triggered successfully", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = NotificationDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while sending notification")})
    @PostMapping("/send-notification")
    public ResponseEntity<ResponseBody<Void>> triggerNotification(/*@Valid*/ @RequestBody NotificationDto notificationDto) {
        LOGGER.info("Entered into triggerNotification method");
        notificationService.sendNotification(notificationDto);
        return new ResponseEntity<ResponseBody<Void>>(ResponseBody.of("Notification triggered Successfully"), HttpStatus.OK);
    }
}
