# 🛒 E-Commerce Microservices Project

## Introduction
This project is a complete e-commerce backend system built using Spring Boot Microservices architecture. It includes core microservices like Product, Cart, Order, Notification, along with Service Registry, API Gateway, and Config Server for efficient management and scaling.

---

## ⚙️ Microservices Overview

- ✅ **Product Service**
- ✅ **Cart Service**
- ✅ **Order Service**
- ✅ **Notification Service**
- ✅ **API Gateway**
- ✅ **Config Server**
- ✅ **Service Registry (Eureka Server)**

---

## 📦 Kafka Setup using Docker

### 🪰 Steps to Set Up Kafka and Zookeeper Using Docker

1. Create a new folder:
   ```bash
   mkdir kafka-setup
   cd kafka-setup
   ```
2. Create a `docker-compose.yaml` file with the following content:

   ```yaml
   version: '3'
   services:
     zookeeper:
       image: confluentinc/cp-zookeeper:latest
       environment:
         ZOOKEEPER_CLIENT_PORT: 2181
         ZOOKEEPER_TICK_TIME: 2000

     kafka:
       image: confluentinc/cp-kafka:latest
       ports:
         - "9092:9092"
       environment:
         KAFKA_BROKER_ID: 1
         KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
         KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
         KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
   ```

3. Run Docker Compose:
   ```bash
   docker-compose up -d
   ```

4. Verify containers:
   ```bash
   docker ps
   ```

5. Access Kafka shell:
   ```bash
   docker exec -it kafka-setup-kafka-1 bash
   ```

6. Create a topic:
   ```bash
   kafka-topics --create --topic order-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
   ```

7. List all topics:
   ```bash
   kafka-topics --list --bootstrap-server localhost:9092
   ```

---

## 🔍 Zipkin Setup using Docker

To set up Zipkin for distributed tracing:

```bash
docker run -d -p 9411:9411 openzipkin/zipkin
```

🔗 Zipkin UI: [http://localhost:9411](http://localhost:9411)

---

## 🪱 Dependencies Used Per Microservice

### 📌 Product Service
- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Eureka Client
- Lombok
- H2 Database
- Spring Boot Actuator
- Zipkin
- Spring Cloud Config

### 📌 Cart Service
- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Eureka Client
- Lombok
- H2 Database
- Spring Boot Actuator
- Zipkin
- Spring Cloud Config

### 📌 Order Service
- Spring Web
- Spring Data JPA
- Spring Boot DevTools
- Eureka Client
- Lombok
- H2 Database
- Spring Boot Actuator
- Zipkin
- Spring Cloud Config
- Spring for Apache Kafka

### 📌 Notification Service
- Spring Web
- Spring Boot DevTools
- Eureka Client
- Lombok
- Spring Boot Actuator
- Zipkin
- Spring Cloud Config
- Spring for Apache Kafka

### 📌 API Gateway
- Spring Boot DevTools
- Eureka Client
- Spring Cloud Gateway (MVC)
- Spring Cloud Config
- Zipkin
- Spring Boot Actuator

### 📌 Config Server
- Spring Boot DevTools
- Spring Cloud Config Server
- Spring Boot Actuator

### 📌 Eureka Server
- Spring Boot DevTools
- Eureka Server
- Spring Boot Actuator

---

## ✅ Final Notes

- All services are properly registered with Eureka.
- Each service communicates via REST or Kafka (where required).
- API Gateway handles routing to all services.
- Centralized configuration is done through Config Server.
- Distributed tracing using Zipkin is enabled.
- Kafka is used for messaging between Order and Notification services.

---

