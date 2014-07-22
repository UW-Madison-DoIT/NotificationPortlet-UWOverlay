package edu.wisc.notification.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.jasig.portlet.notice.NotificationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.wisc.notification.service.NotificationService;

@RestController
public class NotificationMarkAsReadRestController {
    
    @Autowired
    private NotificationService notificationService;
    
    @RequestMapping("/read")
    @ResponseBody
    public String fetchNotifications(HttpServletRequest request, @RequestParam String username, @RequestParam Long notificationId) throws JsonGenerationException, JsonMappingException, IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(notificationService.updateNotificationAsRead(username, notificationId));
    }
}
