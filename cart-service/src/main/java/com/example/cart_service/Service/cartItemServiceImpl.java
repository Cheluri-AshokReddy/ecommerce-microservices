package com.example.cart_service.Service;

import com.MicroServicesEcom.product_service.Exceptions.ResourceNotFoundException;
import com.example.cart_service.DTO.CartItemsDTO;
import com.example.cart_service.DTO.ProductDTO;
import com.example.cart_service.Model.Cart;
import com.example.cart_service.Model.CartItem;
import com.example.cart_service.Repository.cartItemRepository;
import com.example.cart_service.Repository.cartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class cartItemServiceImpl implements cartItemService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private cartItemRepository cartitemrepository;

    @Autowired
    private cartRepository cartrepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CartItemsDTO addCartItem(Long productId, Integer quantity,Long cartId) {

        ProductDTO productDTO=restTemplate.getForObject("http://product-service/product/getproductbyid/" + productId, ProductDTO.class);
        CartItem cartItem=modelMapper.map(productDTO, CartItem.class);
        cartItem.setQuantity(quantity);
        Cart cart=cartrepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","cartId",cartId));
        cartItem.setCart(cart);
        cartItem.setProductId(productId);
        cartitemrepository.save(cartItem);
        Double price=quantity*cartItem.getProductPrice();
        Double previousPrice=cart.getTotalPrice();
        cart.setTotalPrice(previousPrice+price);
        cartrepository.save(cart);
        return modelMapper.map(cartItem, CartItemsDTO.class);
    }

    @Override
    public List<CartItemsDTO> getAllCartItems() {
        List<CartItem>cartItemList=cartitemrepository.findAll();
        List<CartItemsDTO>cartItemsDTOList=cartItemList
                .stream()
                .map(cartitem->modelMapper.map(cartitem, CartItemsDTO.class))
                .toList();

        return cartItemsDTOList;
    }
    @Override
    public List<CartItemsDTO> getCartItemsByCartId(Long cartId) {
        Cart cart=cartrepository.findById(cartId).orElseThrow(()-> new ResourceNotFoundException("Cart","cartId",cartId));
        List<CartItem>cartItemList=cart.getCartItemList();
        List<CartItemsDTO>cartItemsDTOList=cartItemList
                .stream()
                .map(cartitem->modelMapper.map(cartitem, CartItemsDTO.class))
                .toList();

        return cartItemsDTOList;
    }
}
