package com.finance.app.notification.service.service;

import com.finance.app.notification.service.dto.NotificationDto;
import com.finance.app.notification.service.entity.Notification;
import com.finance.app.notification.service.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    @Autowired
    NotificationRepository notificationRepository;

    public void sendNotification(NotificationDto notificationDto) {
        LOGGER.debug("Entered into sendNotification method");
        Notification notification = prepareDto(notificationDto);
        // Trigger Notification
        boolean notificationStatus = triggerNotification(notification);
        notification.setNotificationSent(notificationStatus);
        notificationRepository.save(notification);
    }

    private boolean triggerNotification(Notification notification) {
        LOGGER.debug("Notification triggered");
        LOGGER.debug("Sending notification to user: {} for the expense: {} made towards category: {} by considering the budget: {}", notification.getFkUserId(), notification.getExpenseAmount(), notification.getCategory(), notification.getBudgetAmount());
        // notification call
        return true;
    }

    private Notification prepareDto(NotificationDto notificationDto) {
        Notification notification = new Notification();
        notification.setBudgetAmount(notificationDto.getBudgetAmount());
        notification.setCategory(notificationDto.getCategory());
        notification.setExpenseDescription(notificationDto.getExpenseDescription());
        notification.setFkUserId(notificationDto.getFkUserId());
        notification.setExpenseAmount(notificationDto.getExpenseAmount());
        notification.setTimestamp(LocalDateTime.now());
        return notification;
    }
}
