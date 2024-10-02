package com.ecommerce.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AddProductInCartDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.PlaceOrderDto;
import com.ecommerce.dto.RemoveProductFromCartDto;
import com.ecommerce.exceptions.ValidationException;
import com.ecommerce.services.customer.cart.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CartController {
	
	
	@Autowired
	private CartService cartService;
	
	/*
	@PostMapping("/cart/add")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
	    // Call the service to add the product to the cart
	    ResponseEntity<?> response = cartService.addProductToCart(addProductInCartDto);

	    // Return the response directly from the service method
	    return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	*/
	
	
	
	@PostMapping("/cart/add")
	public ResponseEntity<?> addProductToCart(@RequestBody AddProductInCartDto addProductInCartDto) {
	    try {
	        cartService.addProductToCart(addProductInCartDto);
	        return ResponseEntity.status(HttpStatus.OK).body("Product added to cart successfully");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error adding product to cart");
	    }
	}

	
	
	
	
	@DeleteMapping("/cart/remove")
	public ResponseEntity<?> removeProductFromCart(@RequestBody RemoveProductFromCartDto removeProductFromCartDto) {
	    // Call the service to remove the product from the cart
	    ResponseEntity<?> response = cartService.removeProductFromCart(removeProductFromCartDto);

	    if (response.getStatusCode() == HttpStatus.OK) {
	        // If the product was removed successfully, return NO CONTENT (204)
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    } else if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
	        // If the product was not found, return NOT FOUND (404)
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart");
	    } else {
	        // Handle other cases if needed
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while removing the product");
	    }
	}

	
	@GetMapping("/cart/{userId}")
	public ResponseEntity<?> getCartByUserId(@PathVariable Long userId)
	{
		OrderDto orderDto = cartService.getCartByUserId(userId);
		return ResponseEntity.status(HttpStatus.OK).body(orderDto);
	}
	
	
	@GetMapping("/coupon/{userId}/{code}")
	public ResponseEntity<?> applyCoupon(@PathVariable  Long userId, @PathVariable String code)
	{
		try
		{
			OrderDto orderDto  = cartService.applyCoupon(userId, code);
			return ResponseEntity.ok(orderDto);
		}
		catch(ValidationException ex)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	@PostMapping("/addition")
	public ResponseEntity<OrderDto> increaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addProductInCartDto));
	}
	
	@PostMapping("/deduction")
	public ResponseEntity<OrderDto> decreaseProductQuantity(@RequestBody AddProductInCartDto addProductInCartDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addProductInCartDto));
	}
	
	@PostMapping("/placeOrder")
	public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderDto placeOrderDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
	}
	
	
	@GetMapping("/myOrders/{userId}")
	public ResponseEntity<List<OrderDto>> getMyPlacedOrders(@PathVariable Long userId)
	{
		return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
	}


}
