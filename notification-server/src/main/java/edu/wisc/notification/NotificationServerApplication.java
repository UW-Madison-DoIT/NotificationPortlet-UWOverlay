package edu.wisc.notification;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class NotificationServerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(NotificationServerApplication.class, args);
    }
}