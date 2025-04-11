package com.example.notification_service.Service;

import com.example.notification_service.Model.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {


    @KafkaListener(topics = "order-topic", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void handleOrderPlaced(OrderPlacedEvent event) {
        System.out.println("Received Order: " + event.getOrderId());
        System.out.println("Sending notification to: " + event.getEmail());
    }

}
