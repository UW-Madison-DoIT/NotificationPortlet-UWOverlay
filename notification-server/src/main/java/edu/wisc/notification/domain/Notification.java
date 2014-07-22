package edu.wisc.notification.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Notification implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "TEXT", nullable = false)
    private String notificationText;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification")
    private Set<NotificationGroup> groups = new HashSet<NotificationGroup>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification")
    private Set<NotificationUser> users = new HashSet<NotificationUser>();
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "notification")
    private Set<NotificationStatus> statuses = new HashSet<NotificationStatus>();
    
    protected Notification() {}
    
    public Notification(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public Long getId() {
        return id;
    }

    public Set<NotificationGroup> getGroups() {
        return groups;
    }

    public Set<NotificationStatus> getStatuses() {
        return statuses;
    }

    public Set<NotificationUser> getUsers() {
        return users;
    }
}
