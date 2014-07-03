package edu.wisc.notification.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class NotificationStatus implements Serializable {
    
    private static final long serialVersionUID = 1L;

    public enum Status { READ,UNREAD }
    
    @Id
    @ManyToOne(optional = false)
    private Notification notification;
    
    @Id 
    private String username;
    
    @Column (name = "STATUS", nullable = false)
    private String status;
    
    protected NotificationStatus () {}
    
    public NotificationStatus(Notification note, String user, Status status) {
        this.notification = note;
        this.username = user;
        this.status = status.toString();
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Status getStatus() {
        return status != null ? Status.valueOf(status) : null;
    }

    public void setStatus(Status status) {
        this.status = status.toString();
    }
}
