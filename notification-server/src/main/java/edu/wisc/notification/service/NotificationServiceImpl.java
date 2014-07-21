package edu.wisc.notification.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jasig.portlet.notice.NotificationCategory;
import org.jasig.portlet.notice.NotificationEntry;
import org.jasig.portlet.notice.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisc.notification.domain.Notification;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public NotificationResponse getNotifications(String username, List<String> groups) {
        List <Notification> notifications = new ArrayList<Notification>();
        notifications.addAll(getNotificationsByGroup(username, groups));
        notifications.addAll(getNotificationsByUser(username));
        return transformNotificationListToResponse(notifications);
    }
    
    private Collection<? extends Notification> getNotificationsByUser(String username) {
        return notificationRepository.findByUser(username);
    }

    private List<Notification> getNotificationsByGroup(String username, List<String> groups) {
        List <Notification> notifications = new ArrayList<Notification>();
        for(String group : groups) {
            notifications.addAll(notificationRepository.findByGroup(group));
        }
        return notifications;
    }
    
    private NotificationResponse transformNotificationListToResponse(List<Notification> notifications) {
        NotificationResponse nr = new NotificationResponse();
        NotificationCategory notificationCategory = new NotificationCategory();
        notificationCategory.setTitle("Default Category");
        ArrayList<NotificationEntry> notificationEntries = new ArrayList<NotificationEntry>();
        for(Notification notification : notifications) {
            NotificationEntry ne = new NotificationEntry();
            ne.setId(notification.getId() != null? notification.getId().toString() : null);
            ne.setBody(notification.getNotificationText());
            ne.setTitle(notification.getNotificationText());
            notificationEntries.add(ne);
        }
        notificationCategory.addEntries(notificationEntries);
        
        nr.setCategories(Arrays.asList(notificationCategory));
        
        return nr;
    }
}
