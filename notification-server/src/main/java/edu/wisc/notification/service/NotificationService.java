package edu.wisc.notification.service;

import java.util.List;

import org.jasig.portlet.notice.NotificationResponse;

import edu.wisc.notification.domain.Notification;

public interface NotificationService {
    
    /*
     * Gets a list of notifications based on groups and based on the username. 
     * It then translate them to a NotificationResponse object that contains a
     * category that contains entries for the notifications. This was structured 
     * to communicate with the notification portlet directly. This omits READ
     * notifications
     */
    public NotificationResponse getNotifications(String username, List<String>groups);
    
    /*
     * Gets a list of notifications based on groups and based on the username. 
     * It then translate them to a NotificationResponse object that contains a
     * category that contains entries for the notifications. This was structured 
     * to communicate with the notification portlet directly. This contains all 
     * notifications regardless of state
     */
    public NotificationResponse getAllNotifications(String username, List<String> groups);
    
    /*
     * This is to mark a notification as read.
     */
    public int updateNotificationAsRead(String username, Long notificationId);
    
    public Notification save(Notification n);

    
}
