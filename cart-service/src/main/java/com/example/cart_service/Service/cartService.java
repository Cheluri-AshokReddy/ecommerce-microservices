package com.example.cart_service.Service;

import com.example.cart_service.DTO.CartDTO;
import com.example.cart_service.Model.Cart;

import java.util.List;

public interface cartService {

    public CartDTO addCart(Cart cart);
    public List<CartDTO> getAllCarts();
    public CartDTO deleteCart(Long cartId);
    public CartDTO getCartById(Long cartId);
}
