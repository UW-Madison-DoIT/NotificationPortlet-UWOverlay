package edu.wisc.notification.service;

import java.util.List;

import org.jasig.portlet.notice.NotificationResponse;

public interface NotificationService {
    
    public NotificationResponse getNotifications(String username, List<String>groups);
}
