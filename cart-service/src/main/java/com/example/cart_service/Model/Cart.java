package com.example.cart_service.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartid;

    @OneToMany(mappedBy = "cart",cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},orphanRemoval = true)
    private List<CartItem> cartItemList;
    private Double totalPrice;

    public Long getCartid() {
        return cartid;
    }

    public void setCartid(Long cartid) {
        this.cartid = cartid;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Cart(Long cartid, List<CartItem> cartItemList, Double totalPrice) {
        this.cartid = cartid;
        this.cartItemList = cartItemList;
        this.totalPrice = totalPrice;
    }

    public Cart() {
    }
}
