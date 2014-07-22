package edu.wisc.notification.service;

import org.springframework.data.repository.Repository;

import edu.wisc.notification.domain.NotificationGroup;

public interface NotificationGroupRepository  extends Repository<NotificationGroup, Long>{
    NotificationGroup save(NotificationGroup status);
}
