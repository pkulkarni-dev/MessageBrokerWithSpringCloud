package com.order.service.order_service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {

    private String orderId;
    private String email;
    private String userId;
    private String userPhone;
    private boolean orderPaymentStatus;
    private boolean orderStatus;
    private String courseId;
}
