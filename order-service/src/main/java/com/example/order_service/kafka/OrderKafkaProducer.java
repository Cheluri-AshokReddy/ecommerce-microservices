package com.example.order_service.kafka;


import com.example.order_service.Model.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderKafkaProducer {

    @Autowired
    private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public void sendOrderNotification(OrderPlacedEvent event) {
        kafkaTemplate.send("order-topic", event);
    }
}

