package com.example.cart_service.Controller;

import com.example.cart_service.DTO.CartItemsDTO;
import com.example.cart_service.Model.CartItem;
import com.example.cart_service.Service.cartItemServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItemsController")
public class cartItemController {

    @Autowired
    private cartItemServiceImpl cartitemserviceimpl;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(cartItemController.class);

    @GetMapping ("/addCartItem/{productId}/{quantity}/{cartId}")
    public ResponseEntity<CartItemsDTO> addCartItem(@PathVariable Long productId, @PathVariable Integer quantity,@PathVariable Long cartId) {
        CartItemsDTO addedItem = cartitemserviceimpl.addCartItem(productId,quantity,cartId);
        LOGGER.info("CartItem add: {}", productId);
        return new ResponseEntity<>(addedItem, HttpStatus.CREATED);
    }

    @GetMapping("/getallproducts")
    public ResponseEntity<List<CartItemsDTO>> getAllCartItems() {
        List<CartItemsDTO> items = cartitemserviceimpl.getAllCartItems();
        LOGGER.info("CartItem findAll");
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/cart/{cartId}")
    public ResponseEntity<List<CartItemsDTO>> getCartItemsByCartId(@PathVariable Long cartId) {
        List<CartItemsDTO> items = cartitemserviceimpl.getCartItemsByCartId(cartId);
        LOGGER.info("CartItem find{}: {}", cartId, items);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
}
