package edu.wisc.notification.service;


import java.util.Arrays;

import org.jasig.portlet.notice.NotificationResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.wisc.notification.NotificationServerApplication;
import edu.wisc.notification.domain.Notification;
import edu.wisc.notification.domain.NotificationGroup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationServerApplication.class)
@WebAppConfiguration
public class NotificationServiceIT {

    @Autowired
    NotificationService ns;
    
    @Autowired
    NotificationGroupRepository ngr;
    
    @Test
    public void testNotificationServiceStatusUpdate() {
        Notification n = createNotification(1);
        Assert.assertEquals(ns.updateNotificationAsRead("levett", n.getId()), 1);
    }
    
    @Test
    public void testNewQueryForFilteringOutRead() {
        //create some notifications
        ns.save(createNotification(1));
        Notification n2 = ns.save(createNotification(2));
        // set one as read
        Assert.assertEquals(ns.updateNotificationAsRead("levett", n2.getId()), 1);
        NotificationResponse nr = ns.getAllNotifications("levett", Arrays.asList("uw:domain:my.wisc.edu:my_uw_administrators"));
        
        NotificationResponse nr2 = ns.getNotifications("levett", Arrays.asList("uw:domain:my.wisc.edu:my_uw_administrators"));
        
        int nr1Size = nr.getCategories().get(0).getEntries().size();
        int nr2Size = nr2.getCategories().get(0).getEntries().size();
        Assert.assertNotEquals(nr1Size, nr2Size);
    }

    private Notification createNotification(int count) {
        Notification n = new Notification("Some awesome Info #" + count);
        NotificationGroup notificationGroup = new NotificationGroup("uw:domain:my.wisc.edu:my_uw_administrators", n);
        n = ns.save(n);
        n.getGroups().add(notificationGroup);
        ngr.save(notificationGroup);
        n = ns.save(n);
        return n;
    }
}
