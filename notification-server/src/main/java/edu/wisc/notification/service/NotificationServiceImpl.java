package edu.wisc.notification.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.jasig.portlet.notice.NotificationCategory;
import org.jasig.portlet.notice.NotificationEntry;
import org.jasig.portlet.notice.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.wisc.notification.domain.Notification;
import edu.wisc.notification.domain.NotificationStatus;

@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private NotificationStatusRepository notificationStatusRepository;

    @Override
    public NotificationResponse getNotifications(String username, List<String> groups) {
        return getNotifications(username, groups, false);
    }
    
    @Override
    public NotificationResponse getAllNotifications(String username, List<String> groups) {
        return getNotifications(username, groups, true);
    }
    
    private NotificationResponse getNotifications(String username, List<String> groups, boolean withRead) {
        List <Notification> notifications = new ArrayList<Notification>();
        notifications.addAll(getNotificationsByGroup(username, groups, withRead));
        notifications.addAll(getNotificationsByUser(username, withRead));
        return transformNotificationListToResponse(notifications);
    }
    
    private Collection<? extends Notification> getNotificationsByUser(String username, boolean withRead) {
        if(withRead)
            return notificationRepository.findByUser(username);
        else
            return notificationRepository.findByUserNoRead(username);
    }

    private List<Notification> getNotificationsByGroup(String username, List<String> groups, boolean withRead) {
        List <Notification> notifications = new ArrayList<Notification>();
        for(String group : groups) {
            if(withRead)
                notifications.addAll(notificationRepository.findByGroup(group));
            else
                notifications.addAll(notificationRepository.findByGroupNoRead(group));
        }
        return notifications;
    }
    
    @Override
    @Transactional
    public int updateNotificationAsRead(String username, Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId);
        if(notification != null) {
            //TODO: Brute force, make better, like CollectionUtils.find or utilizing the database to check
            for(NotificationStatus status: notification.getStatuses()) {
                if(status.getUsername().equalsIgnoreCase(username)) {
                    if(status.getStatus().equals(NotificationStatus.Status.READ)) {
                        return 0;//found it and its already read
                    }
                    status.setStatus(NotificationStatus.Status.READ);
                    notificationStatusRepository.save(status);
                    notificationRepository.save(notification);
                    return 1;//found it and updated
                }
            }
            NotificationStatus status = new NotificationStatus(notification, username, NotificationStatus.Status.READ);
            notification.getStatuses().add(status);
            notificationStatusRepository.save(status);
            notificationRepository.save(notification);
            return 1;//didn't find it so creating
        }
        return 0; //didn't find it
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

    @Override
    public Notification save(Notification n) {
        return notificationRepository.save(n);
    }
}
