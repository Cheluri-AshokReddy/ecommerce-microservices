package com.example.cart_service.DTO;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {
    private Long cartId;
    private Double totalPrice=0.0;
    private List<CartItemsDTO> products=new ArrayList<>();

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<CartItemsDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CartItemsDTO> products) {
        this.products = products;
    }
}
