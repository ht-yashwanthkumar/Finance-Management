package com.finance.app.notification.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finance.app.notification.service.PayloadHelper;
import com.finance.app.notification.service.dto.NotificationDto;
import com.finance.app.notification.service.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void triggerNotification_URL_Exists() throws Exception {
        NotificationDto notificationDto = PayloadHelper.mockNotificationDto();
        String content = new ObjectMapper().writeValueAsString(notificationDto);
        mockMvc.perform(post("/notification/send-notification")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Mockito.verify(notificationService, Mockito.times(1)).sendNotification(Mockito.any());
    }

    @Test
    public void triggerNotification_URL_NOT_Exists() throws Exception {
        NotificationDto notificationDto = PayloadHelper.mockNotificationDto();
        String content = new ObjectMapper().writeValueAsString(notificationDto);
        mockMvc.perform(post("/notifications/send-notification")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        Mockito.verify(notificationService, Mockito.times(0)).sendNotification(Mockito.any());
    }

}
