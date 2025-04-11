package com.example.order_service.Controller;

import com.example.order_service.DTO.OrderDTO;
import com.example.order_service.DTO.OrderRequestDTO;
import com.example.order_service.Service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(OrderController.class);

    @PostMapping("/order/{paymentMethod}/{cartId}")
    public ResponseEntity<OrderDTO> orderProducts(@PathVariable String paymentMethod
                                                  ,@PathVariable Long cartId
            ,@RequestBody OrderRequestDTO orderRequestDTO){
        OrderDTO orderdto=orderService.placeOrder(
                orderRequestDTO.getAddressId(),
                paymentMethod,
                orderRequestDTO.getPgName(),
                orderRequestDTO.getPgPaymentId(),
                orderRequestDTO.getPgStatus(),
                orderRequestDTO.getPgResponseMessage(),
                cartId

        );
        LOGGER.info("Order PLaced");
        return new ResponseEntity<>(orderdto, HttpStatus.OK);
    }
}
