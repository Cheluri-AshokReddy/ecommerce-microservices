package com.example.notification_service.Model;


public class OrderPlacedEvent {
    private Long orderId;
    private String email;
    private String status;

    public OrderPlacedEvent() {
    }

    public OrderPlacedEvent(Long orderId, String email, String status) {
        this.orderId = orderId;
        this.email = email;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderPlacedEvent{" +
                "orderId='" + orderId + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

