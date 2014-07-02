package edu.wisc.notification.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NotificationGroup implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne(optional = false)
    private Notification notification;
    
    @Id
    @Column (name = "GRP_NAME")
    private String groupName;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
