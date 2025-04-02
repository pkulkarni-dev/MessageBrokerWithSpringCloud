package com.notification.service.notification_service.functions;

import com.notification.service.notification_service.dto.OrderInformation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class NotificationService {

    @Bean
    public Supplier<String> testing() {
        return () -> "This is testing";
    }

    @Bean
    public Function<String, String> sayHello() {
        return (msg) -> "Hello, how are you? " + msg;
    }

    @Bean
    public Function<OrderInformation, String> orderNotification() {

        return orderInformation -> {
            sendNotification(orderInformation);
            System.out.println(orderInformation.getEmailId());
            System.out.println(orderInformation.getPrice());
            System.out.println(orderInformation.getUserPhone());
            System.out.println(orderInformation.getCreatedDate());
            return orderInformation.getUserPhone();
        };

    }

    @Bean
    public Function<Map<String, String>, Set<Map.Entry<String, String>>> orderNotificationMap() {

        return orderNotificationMap -> {
            //sendNotification(orderNotificationMap);
            return orderNotificationMap.entrySet();
        };

    }

    private void sendNotification(OrderInformation orderInformation) {

    }
}
