package edu.wisc.notification.service;


import java.util.Arrays;
import java.util.Set;

import org.jasig.portlet.notice.NotificationResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.wisc.notification.NotificationServerApplication;
import edu.wisc.notification.domain.Notification;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = NotificationServerApplication.class)
@WebAppConfiguration
public class NotificationServiceTest {

    @Autowired
    NotificationService ns;
    
    @Test
    public void testNotificationServiceStatusUpdate() {
        Notification n = ns.save(createNotification());
        
        Assert.assertEquals(ns.updateNotificationAsRead("levett", n.getId()), 1);
    }
    
    @Test
    public void testNewQueryForFilteringOutRead() {
        NotificationResponse nr = ns.getAllNotifications("levett", Arrays.asList("uw:domain:my.wisc.edu:my_uw_administrators"));
        
        NotificationResponse nr2 = ns.getNotifications("levett", Arrays.asList("uw:domain:my.wisc.edu:my_uw_administrators"));
        
        int nr1Size = nr.getCategories().get(0).getEntries().size();
        int nr2Size = nr2.getCategories().get(0).getEntries().size();
        Assert.assertNotEquals(nr1Size, nr2Size);
    }

    private Notification createNotification() {
        Notification n = new Notification("Some awesome Info");
        return n;
    }
}
