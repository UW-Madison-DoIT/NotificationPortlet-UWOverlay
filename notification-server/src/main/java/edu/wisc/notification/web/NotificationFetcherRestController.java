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
public class NotificationFetcherRestController {
    
    @Autowired
    private NotificationService notificationService;
    private final String GROUPSEPERATORCHARS = ";";
    
    @RequestMapping("/fetch")
    @ResponseBody
    public String fetchNotifications(HttpServletRequest request, @RequestParam String username, @RequestParam String groups) throws JsonGenerationException, JsonMappingException, IOException {
        //call service to get notifications that returns the notification response object
        NotificationResponse notifications = notificationService.getAllNotifications(username, splitGroups(groups));
        return notificationsToJson(notifications);
    }
    
    @RequestMapping("/fetchRead")
    @ResponseBody
    public String fetchReadNotifications(HttpServletRequest request, @RequestParam String username, @RequestParam String groups) throws JsonGenerationException, JsonMappingException, IOException {
        //call service to get read notifications that returns the notification response object
        NotificationResponse notifications = notificationService.getOnlyReadNotifications(username, splitGroups(groups));
        return notificationsToJson(notifications);
    }
    
    @RequestMapping("/fetchUnread")
    @ResponseBody
    public String fetchUnreadNotifications(HttpServletRequest request, @RequestParam String username, @RequestParam String groups) throws JsonGenerationException, JsonMappingException, IOException {
        //call service to get unread notifications that returns the notification response object
        NotificationResponse notifications = notificationService.getNotifications(username, splitGroups(groups));
        return notificationsToJson(notifications);
    }
    
    private List<String> splitGroups(String groups){
        return new ArrayList<String>(Arrays.asList(StringUtils.split(groups, GROUPSEPERATORCHARS)));
    }
    
    private String notificationsToJson(NotificationResponse response) throws JsonGenerationException, JsonMappingException, IOException{
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(response);
    }
}
