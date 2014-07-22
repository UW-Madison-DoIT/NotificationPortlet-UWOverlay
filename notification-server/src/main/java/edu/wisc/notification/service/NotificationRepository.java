package edu.wisc.notification.service;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.wisc.notification.domain.Notification;

interface NotificationRepository extends Repository<Notification, Long>{
    Notification findById(Long id);
    
    @Query("SELECT n FROM Notification n JOIN n.users u where u.userName = :user")
    Set<Notification> findByUser(@Param(value = "user") String username);
    
    @Query("SELECT n FROM Notification n JOIN n.users u LEFT JOIN n.statuses s where u.userName = :user and (s.status is null or s.status != 'READ')")
    Set<Notification> findByUserNoRead(@Param(value = "user") String username);
    
    @Query("SELECT n FROM Notification n JOIN n.users u LEFT JOIN n.statuses s where u.userName = :user and s.status = 'READ'")
    Set<Notification> findByUserOnlyRead(@Param(value = "user") String username);
    
    @Query("SELECT n FROM Notification n JOIN n.groups g where g.groupName = :group")
    Set<Notification> findByGroup(@Param(value = "group") String group);
    
    @Query("SELECT n FROM Notification n JOIN n.groups g LEFT JOIN n.statuses s where g.groupName = :group and (s.status is null or s.status != 'READ')")
    Set<Notification> findByGroupNoRead(@Param(value = "group") String group);
    
    @Query("SELECT n FROM Notification n JOIN n.groups g LEFT JOIN n.statuses s where g.groupName = :group and s.status = 'READ'")
    Set<Notification> findByGroupOnlyRead(@Param(value = "group") String group);
    
    Notification save(Notification notification);
}
