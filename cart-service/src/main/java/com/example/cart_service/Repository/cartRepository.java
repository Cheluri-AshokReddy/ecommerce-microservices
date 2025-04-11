package com.example.cart_service.Repository;

import com.example.cart_service.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface cartRepository extends JpaRepository<Cart, Long> {
}
