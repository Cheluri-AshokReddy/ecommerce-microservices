package com.example.order_service.Service;

import com.example.order_service.DTO.OrderDTO;

public interface OrderService {

    OrderDTO placeOrder(Long addressId, String paymentMethod, String pgName, String pgPaymentId, String pgStatus, String pgResponseMessage,Long cartId);
}
