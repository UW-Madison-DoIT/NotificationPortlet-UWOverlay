package edu.wisc.notification.service;

import java.util.List;

import org.jasig.portlet.notice.NotificationResponse;

public interface NotificationService {
    
    /*
     * Gets a list of notifications based on groups and based on the username. 
     * It then translate them to a NotificationResponse object that contains a
     * category that contains entries for the notifications. This was structured 
     * to communicate with the notification portlet directly.
     */
    public NotificationResponse getNotifications(String username, List<String>groups);
    
    /*
     * This is to mark a notification as read.
     */
    public int updateNotificationAsRead(String username, Long notificationId);
}
