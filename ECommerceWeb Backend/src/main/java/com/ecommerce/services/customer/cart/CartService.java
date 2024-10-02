package com.ecommerce.services.customer.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;

import com.ecommerce.dto.AddProductInCartDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.PlaceOrderDto;
import com.ecommerce.dto.RemoveProductFromCartDto;

public interface CartService {
	
	ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);
	ResponseEntity<?> removeProductFromCart(RemoveProductFromCartDto removeProductFromCartDto);
	
	
	OrderDto getCartByUserId(Long userId);
	
	OrderDto applyCoupon(Long userId, String code);
	
	OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);
	
	OrderDto placeOrder(PlaceOrderDto placeOrderDto);
	
	List<OrderDto> getMyPlacedOrders(Long userId);
	
	OrderDto searchOrderByTrackingId(UUID trackingId);



	

}
