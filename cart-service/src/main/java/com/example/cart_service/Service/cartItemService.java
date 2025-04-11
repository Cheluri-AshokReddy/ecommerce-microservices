package com.example.cart_service.Service;

import com.example.cart_service.DTO.CartItemsDTO;
import com.example.cart_service.Model.CartItem;

import java.util.List;

public interface cartItemService {

    public CartItemsDTO addCartItem(Long productId, Integer quantity,Long cartId);
    public List<CartItemsDTO> getAllCartItems();
    public List<CartItemsDTO> getCartItemsByCartId(Long cartId );


}
