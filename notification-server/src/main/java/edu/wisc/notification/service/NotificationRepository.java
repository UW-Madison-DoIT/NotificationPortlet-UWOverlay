package edu.wisc.notification.service;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.wisc.notification.domain.Notification;

interface NotificationRepository extends Repository<Notification, Long>{
    Notification findById(Long id);
    
    @Query("SELECT n FROM Notification n JOIN n.groups g where g.groupName = :group")
    Set<Notification> findByGroup(@Param(value = "group") String group);
    
    Notification save(Notification notification);
}
