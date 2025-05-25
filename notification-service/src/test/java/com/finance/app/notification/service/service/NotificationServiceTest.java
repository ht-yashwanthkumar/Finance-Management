package com.finance.app.notification.service.service;

import com.finance.app.notification.service.PayloadHelper;
import com.finance.app.notification.service.entity.Notification;
import com.finance.app.notification.service.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Mock
    private NotificationRepository notificationRepository;

    @Captor
    ArgumentCaptor<Notification> notificationCaptor;

    @Test
    public void testSendNotification() {
        Mockito.when(notificationRepository.save(Mockito.any())).thenReturn(PayloadHelper.mockNotification(101));
        notificationService.sendNotification(PayloadHelper.mockNotificationDto());
        verify(notificationRepository, times(1)).save(notificationCaptor.capture());
        Notification savedNotification = notificationCaptor.getValue();
        assertNotNull(savedNotification);
    }
}
