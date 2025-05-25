package com.finance.app.notification.service;

import com.finance.app.notification.service.dto.NotificationDto;
import com.finance.app.notification.service.entity.Notification;

import java.time.LocalDateTime;

public class PayloadHelper {

    public static NotificationDto mockNotificationDto() {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setCategory("Groceries");
        notificationDto.setBudgetAmount(10000.00);
        notificationDto.setExpenseAmount(1000.00);
        notificationDto.setExpenseDescription("Purchases made towards groceries");
        notificationDto.setFkUserId(101);
        return notificationDto;
    }

    public static Notification mockNotification(long pkNotificationId) {
        Notification notification = new Notification();
        //notification.setPkNotificationId(pkNotificationId);
        notification.setNotificationSent(Boolean.TRUE);
        notification.setCategory("Groceries");
        notification.setFkUserId(101l);
       // notification.setTimestamp(LocalDateTime.now());
        notification.setExpenseDescription("Purchases made towards groceries");
        notification.setBudgetAmount(10000.00);
        notification.setExpenseAmount(1000.00);
        return notification;
    }
}
