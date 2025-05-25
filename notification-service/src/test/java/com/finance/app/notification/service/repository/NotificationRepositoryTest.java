package com.finance.app.notification.service.repository;

import com.finance.app.notification.service.PayloadHelper;
import com.finance.app.notification.service.entity.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class NotificationRepositoryTest {

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testSaveNotification() {
        Notification savedNotification = notificationRepository.save(PayloadHelper.mockNotification(101));
        assertNotNull(savedNotification);
    }

    @Test
    void testFindById_WhenExists() {
        Notification notification = notificationRepository.save(PayloadHelper.mockNotification(101));
        Optional<Notification> notificationOptional = notificationRepository.findById(notification.getPkNotificationId());
        assertTrue(notificationOptional.isPresent());
    }

    @Test
    void testFindById_WhenNotExists() {
        Optional<Notification> optionalNotification = notificationRepository.findById(999L);
        assertFalse(optionalNotification.isPresent());
    }

    @Test
    void testFindAll() {
        notificationRepository.save(PayloadHelper.mockNotification(101));
        List<Notification> notifications = notificationRepository.findAll();
        assertFalse(notifications.isEmpty());
    }
}
