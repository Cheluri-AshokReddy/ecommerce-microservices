package com.example.order_service.Service;

import com.MicroServicesEcom.product_service.Exceptions.APIException;
import com.example.order_service.DTO.CartDTO;
import com.example.order_service.DTO.CartItemsDTO;
import com.example.order_service.DTO.OrderDTO;
import com.example.order_service.DTO.OrderItemDTO;
import com.example.order_service.Exceptions.ResourceNotFoundException;
import com.example.order_service.Model.*;
import com.example.order_service.Repository.AddressRegistery;
import com.example.order_service.Repository.OrderItemRepository;
import com.example.order_service.Repository.OrderRepository;
import com.example.order_service.Repository.PaymentRepository;
import com.example.order_service.kafka.OrderKafkaProducer;
import com.netflix.discovery.converters.Auto;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AddressRegistery addressRegistery;

    @Autowired
    private OrderKafkaProducer orderKafkaProducer;

    @Override
    @Transactional
    public OrderDTO placeOrder(Long addressId, String paymentMethod, String pgName,
                               String pgPaymentId, String pgStatus,
                               String pgResponseMessage,Long cartId) {

        CartDTO cartDTO = restTemplate.getForObject(
                "http://cart-service/cartcontroller/getCartById/{cartId}",
                CartDTO.class,
                cartId
        );

        Address address=addressRegistery.findById(addressId).orElseThrow(
                ()-> new ResourceNotFoundException("Address","addressId",addressId)
        );
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setTotalAmount(cartDTO.getTotalPrice());
        order.setOrderStatus("Order Accepted !");
        order.setAddress(address);

        Payment payment = new Payment(paymentMethod, pgPaymentId, pgStatus, pgResponseMessage, pgName);
        payment.setOrder(order);
        payment = paymentRepository.save(payment);
        order.setPayment(payment);
        List<CartItemsDTO> cartItems = cartDTO.getProducts();

        if (cartItems.isEmpty()) {
            throw new APIException("Cart is empty");
        }
        List<OrderItem> orderItems = new ArrayList<>();
        for(CartItemsDTO cartItem : cartItems ) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(cartItem.getProductId());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(order);
            orderItems.add(orderItem);
        }
        orderItems = orderItemRepository.saveAll(orderItems);
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);



        OrderDTO orderDTO = modelMapper.map(savedOrder, OrderDTO.class);
        List<OrderItemDTO>orderItemDTOS=orderItems
                .stream()
                .map(orderitem->modelMapper.map(orderitem,OrderItemDTO.class))
                .toList();
        orderDTO.setOrderItems(orderItemDTOS);
        orderDTO.setEmail(address.getEmail());

        OrderPlacedEvent event = new OrderPlacedEvent(
                orderDTO.getOrderId(),
                orderDTO.getEmail(),
                "PLACED"
        );
        orderKafkaProducer.sendOrderNotification(event);
        return orderDTO;
    }
}
