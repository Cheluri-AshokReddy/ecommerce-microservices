package com.example.cart_service.Controller;

import com.example.cart_service.DTO.CartDTO;
import com.example.cart_service.Model.Cart;
import com.example.cart_service.Service.cartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartcontroller")
public class cartController {

    @Autowired
    private cartServiceImpl cartserviceimpl;

    private static final Logger LOGGER
            = LoggerFactory.getLogger(cartController.class);

    @PostMapping("/addCart")
    public ResponseEntity<CartDTO> addCart(@RequestBody Cart cart){
        LOGGER.info("Cart add: {}", cart);
        CartDTO cartdto = cartserviceimpl.addCart(cart);
        return new ResponseEntity<>(cartdto, HttpStatus.CREATED);
    }

    @GetMapping("/getAllCarts")
    public ResponseEntity<List<CartDTO>> getAllCarts(){
        List<CartDTO>cartDTOSList=cartserviceimpl.getAllCarts();
        LOGGER.info("Cart findAll");
        return new ResponseEntity<>(cartDTOSList,HttpStatus.OK);
    }

    @GetMapping("/getCartById/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable Long cartId){
        CartDTO cartDTO = cartserviceimpl.getCartById(cartId);
        LOGGER.info("Cart find: id={}", cartId);
        return new ResponseEntity<>(cartDTO,HttpStatus.OK);
    }

    @DeleteMapping("/deleteCartById/{cartId}")
    public ResponseEntity<String> deleteCartById(@PathVariable Long cartId){
        CartDTO cartDTO = cartserviceimpl.deleteCart(cartId);
        LOGGER.info("Cart delete: {}", cartId);
        return new ResponseEntity<>("Cart Deleted Successfully",HttpStatus.OK);
    }
}
