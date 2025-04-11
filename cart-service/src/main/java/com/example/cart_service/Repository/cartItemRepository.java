package com.example.cart_service.Repository;

import com.example.cart_service.Model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface cartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemByCart_Cartid(Long cartid);


}
