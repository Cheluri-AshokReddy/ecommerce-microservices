package com.example.cart_service.Service;

import com.MicroServicesEcom.product_service.Exceptions.ResourceNotFoundException;
import com.example.cart_service.DTO.CartDTO;
import com.example.cart_service.DTO.CartItemsDTO;
import com.example.cart_service.Model.Cart;
import com.example.cart_service.Model.CartItem;
import com.example.cart_service.Repository.cartItemRepository;
import com.example.cart_service.Repository.cartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class cartServiceImpl implements cartService {

    @Autowired
    private cartRepository cartrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private cartItemRepository cartitemrepository;

    @Override
    public CartDTO addCart(Cart cart) {
        cartrepository.save(cart);
        return modelMapper.map(cart, CartDTO.class);
    }

    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartrepository.findAll();
        List<CartDTO> cartDTOs = new ArrayList<>();
        for (Cart cart : carts) {
            List<CartItem>cartItemList=cartitemrepository.findCartItemByCart_Cartid(cart.getCartid());
            CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
            List<CartItemsDTO>cartItemsDTOs=cartItemList
                    .stream()
                            .map(cartitem->modelMapper.map(cartitem,CartItemsDTO.class))
                                    .toList();
            cartDTO.setProducts(cartItemsDTOs);
            cartDTOs.add(cartDTO);
        }

        return cartDTOs;
    }

    @Override
    public CartDTO deleteCart(Long cartId) {
        Cart cart=cartrepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","cartId",cartId));
        cartrepository.delete(cart);
        return modelMapper.map(cart,CartDTO.class);
    }

    @Override
    public CartDTO getCartById(Long cartId) {
        Cart cart=cartrepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","cartId",cartId));
        List<CartItem>cartItemList=cartitemrepository.findCartItemByCart_Cartid(cart.getCartid());
        List<CartItemsDTO>cartItemsDTOs=cartItemList
                .stream()
                .map(cartitem->modelMapper.map(cartitem,CartItemsDTO.class))
                .toList();
        CartDTO cartDTO = modelMapper.map(cart, CartDTO.class);
        cartDTO.setProducts(cartItemsDTOs);
        return cartDTO;
    }
}
