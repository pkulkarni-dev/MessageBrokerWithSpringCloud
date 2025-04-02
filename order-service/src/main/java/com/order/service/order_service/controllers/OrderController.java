package com.order.service.order_service.controllers;

import com.order.service.order_service.services.OrderService;
import com.order.service.order_service.vo.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDetail orderDetail) {
        //order create login
        OrderDetail order = orderService.createOrder(orderDetail);
        //send notification to notification service; so that notification service send the email and message to user
        orderCreatedNotification(order);
        return ResponseEntity.ok("Order Created");
    }

    @Autowired
    private StreamBridge streamBridge;

    private void orderCreatedNotification(OrderDetail orderDetail) {
        //logic to send notification to notification service
        System.out.println(orderDetail.getUserPhone());
        boolean send = streamBridge.send("orderCreatedEvent-out-0", orderDetail);
        if (send) {
            System.out.println("Order success event is successfully sent to notification service");
        } else {
            System.out.println("Event fail:");
        }

    }
}
