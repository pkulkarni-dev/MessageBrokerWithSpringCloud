package com.order.service.order_service.services;

import com.order.service.order_service.vo.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    public OrderDetail createOrder() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(UUID.randomUUID().toString());
        orderDetail.setEmail("pk@gmail.com");
        orderDetail.setUserPhone("98987989");
        orderDetail.setUserPhone("userId");
        orderDetail.setCourseId(UUID.randomUUID().toString());
        //TODO: save this to db

        return orderDetail;
    }

    public OrderDetail createOrder(OrderDetail orderDetail) {

        return orderDetail;
    }
}
