package edu.wisc.notification.service;

import java.util.List;

import org.jasig.portlet.notice.NotificationResponse;

import edu.wisc.notification.domain.Notification;

public interface NotificationService {
    
    /**
     * Get a list of all UNREAD notifications
     * @param username
     * @param groups
     * @return Returns a Notification response object with a single category
     */
    public NotificationResponse getNotifications(String username, List<String>groups);
    
    /**
     * Get a list of all notifications regardless of state
     * @param username
     * @param groups
     * @return Returns a Notification response object with a single category
     */
    public NotificationResponse getAllNotifications(String username, List<String> groups);
    
    /**
     * Get a list of all READ notifications
     * @param username
     * @param groups
     * @return Returns a Notification response object with a single category
     */
    public NotificationResponse getOnlyReadNotifications(String username, List<String> groups);
    
    /**
     * Makes a notification read by a user
     * @param username
     * @param notificationId
     * @return returns how many updates were made, 0 or 1
     */
    public int updateNotificationAsRead(String username, Long notificationId);
    
    /**
     * It does exactly what you think it does
     * @param n
     * @return
     */
    public Notification save(Notification n);

    

    
}
