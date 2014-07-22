package edu.wisc.notification.service;

import org.springframework.data.repository.Repository;

import edu.wisc.notification.domain.NotificationStatus;

public interface NotificationStatusRepository  extends Repository<NotificationStatus, Long>{
    NotificationStatus save(NotificationStatus status);
}
