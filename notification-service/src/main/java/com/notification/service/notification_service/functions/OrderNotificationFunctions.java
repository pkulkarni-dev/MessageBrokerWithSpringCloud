package com.notification.service.notification_service.functions;

import com.notification.service.notification_service.vo.OrderDetail;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class OrderNotificationFunctions {

    @Bean
    public Function<OrderDetail, String> orderEventReceiver() {
        return (orderDetail -> {
            //process
            logicToSendEmailAndMessageToUser(orderDetail.getEmail(), orderDetail.getUserPhone());
            return "order notification sent to user";
        });
    }

    private void logicToSendEmailAndMessageToUser(String email, String userPhone) {
        System.out.println("Sending Email to " + email);
        System.out.println("Sending sms to " + userPhone);
        System.out.println("notification sent to user");
        System.out.println("--------------------------");
    }

    @Bean
    public TopicExchange orderCreatedExchange() {
        return new TopicExchange("order.created");
    }

    @Bean
    public Queue orderCreatedQueue() {
        return new Queue("order.created.queue", true);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(RabbitTemplate rabbitTemplate) {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(rabbitTemplate);
        rabbitAdmin.declareExchange(orderCreatedExchange());
        rabbitAdmin.declareQueue(orderCreatedQueue());
        return rabbitAdmin;
    }
}
