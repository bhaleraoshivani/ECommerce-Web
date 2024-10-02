package com.ecommerce.services.customer.cart;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.AddProductInCartDto;
import com.ecommerce.dto.CartItemsDto;
import com.ecommerce.dto.OrderDto;
import com.ecommerce.dto.PlaceOrderDto;
import com.ecommerce.dto.RemoveProductFromCartDto;
import com.ecommerce.entitiy.CartItems;
import com.ecommerce.entitiy.Coupon;
import com.ecommerce.entitiy.Order;
import com.ecommerce.entitiy.Product;
import com.ecommerce.entitiy.User;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.exceptions.ValidationException;
import com.ecommerce.repository.CartItemsRepository;
import com.ecommerce.repository.CouponRepository;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.UserRepository;

@Service

public class CartServiceImpl implements CartService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartItemsRepository cartItemRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	

	public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto)
	{
		Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),OrderStatus.Pending);
		
		Optional<CartItems> optionalCartItems = cartItemRepository.findByProductIdAndOrderIdAndUserId
				(addProductInCartDto.getProductId(), activeOrder.getId(),addProductInCartDto.getUserId());
		
		if(optionalCartItems.isPresent())
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
		}
		else
		{
			Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
			Optional<User> optionalUser = userRepository.findById(addProductInCartDto.getUserId());
			
			if(optionalProduct.isPresent() && optionalUser.isPresent())
			{
				CartItems cart = new CartItems();
				cart.setProduct(optionalProduct.get());
				cart.setPrice(optionalProduct.get().getPrice());
				cart.setQuantity(1L);
				cart.setUser(optionalUser.get());
				cart.setOrder(activeOrder);
				
				
				CartItems updatedCart = cartItemRepository.save(cart);
				
				activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
				activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
				activeOrder.getCartItems().add(cart);
				
				orderRepository.save(activeOrder);
				
				return ResponseEntity.status(HttpStatus.CREATED).body(cart);
			}
			else
			{
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
			}
				
		}
		
	}
	
	
	 public ResponseEntity<?> removeProductFromCart(RemoveProductFromCartDto removeProductFromCartDto) {
	        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(removeProductFromCartDto.getUserId(), OrderStatus.Pending);

	        Optional<CartItems> optionalCartItems = cartItemRepository.findByProductIdAndOrderIdAndUserId(
	            removeProductFromCartDto.getProductId(), activeOrder.getId(), removeProductFromCartDto.getUserId()
	        );

	        if (optionalCartItems.isPresent()) {
	            CartItems cartItem = optionalCartItems.get();
	            
	            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - cartItem.getPrice());
	            activeOrder.setAmount(activeOrder.getAmount() - cartItem.getPrice());
	            activeOrder.getCartItems().remove(cartItem);

	            cartItemRepository.delete(cartItem);
	            orderRepository.save(activeOrder);

	            return ResponseEntity.status(HttpStatus.OK).body("Product removed from cart successfully");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found in cart");
	        }
	    }

	
	
	
	
	
	
	
	
	
	

	public OrderDto getCartByUserId(Long userId)
	{
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.Pending);
			
		List<CartItemsDto> cartItemsDtoList = activeOrder.getCartItems().stream().map(CartItems::getCartDto).collect(Collectors.toList());
	    OrderDto  orderDto = new OrderDto();
		orderDto.setAmount(activeOrder.getAmount());
		orderDto.setId(activeOrder.getId());
		orderDto.setOrderStatus(activeOrder.getOrderStatus());
		orderDto.setDiscount(activeOrder.getDiscount());
		orderDto.setTotalAmount(activeOrder.getTotalAmount());	
		orderDto.setCartItems(cartItemsDtoList);
		
		if(activeOrder.getCoupon() != null)
		{
			orderDto.setCouponName(activeOrder.getCoupon().getName());
		}
			
		return orderDto;
			
	}
	

	public  OrderDto applyCoupon(Long userId, String code)
	{
		 Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId,OrderStatus.Pending);
		 
		 Coupon coupon = couponRepository.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found."));
		 
		 if(couponIsExpired(coupon))
		 {
			 throw new ValidationException("Coupon has Expired");
		 }
		 
		 double discountAmount = ((coupon.getDiscount() / 100.0) * activeOrder.getTotalAmount());
		 
		 double netAmount = activeOrder.getTotalAmount() - discountAmount;
		 
		 activeOrder.setAmount((long)netAmount);
		 activeOrder.setDiscount((long)discountAmount);
		 activeOrder.setCoupon(coupon);
		 
		 orderRepository.save(activeOrder);
		 
		 return activeOrder.getOrderDto();
		 
		 
	}
	
	
	private boolean couponIsExpired(Coupon coupon)
	{
		Date currentDate = new Date();
		
		Date expirationDate = coupon.getExpirationDate();
		
		return expirationDate != null && currentDate.after(expirationDate);
	}
	

	public OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		 Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),OrderStatus.Pending);
		 
		 Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
		 
		 Optional<CartItems> optionalCartItems = cartItemRepository.findByProductIdAndOrderIdAndUserId(
				 addProductInCartDto.getProductId(), activeOrder.getId() , addProductInCartDto.getUserId());
		 
		 if(optionalProduct.isPresent() && optionalCartItems.isPresent())
		 {
			 CartItems cartItems = optionalCartItems.get();
			 Product product =  optionalProduct.get();
			 
			 activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
			 activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());
			 
			 cartItems.setQuantity(cartItems.getQuantity() + 1);
			 
			 if(activeOrder.getCoupon() != null)
			 {
				 double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
			
				 double netAmount = activeOrder.getTotalAmount() - discountAmount;
				 
				 activeOrder.setAmount((long)netAmount);
				 activeOrder.setDiscount((long)discountAmount);
			 }
			 cartItemRepository.save(cartItems);
			 orderRepository.save(activeOrder);
			 return activeOrder.getOrderDto();
			 
		 }
		 return null;
	}
	
	
	
	

	public OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto)
	{
		 Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(),OrderStatus.Pending);
		 
		 Optional<Product> optionalProduct = productRepository.findById(addProductInCartDto.getProductId());
		 
		 Optional<CartItems> optionalCartItems = cartItemRepository.findByProductIdAndOrderIdAndUserId(
				 addProductInCartDto.getProductId(), activeOrder.getId() , addProductInCartDto.getUserId());
		 
		 if(optionalProduct.isPresent() && optionalCartItems.isPresent())
		 {
			 CartItems cartItems = optionalCartItems.get();
			 Product product =  optionalProduct.get();
			 
			 activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
			 activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());
			 
			 cartItems.setQuantity(cartItems.getQuantity() - 1);
			 
			 if(activeOrder.getCoupon() != null)
			 {
				 double discountAmount = ((activeOrder.getCoupon().getDiscount() / 100.0) * activeOrder.getTotalAmount());
			
				 double netAmount = activeOrder.getTotalAmount() - discountAmount;
				 
				 activeOrder.setAmount((long)netAmount);
				 activeOrder.setDiscount((long)discountAmount);
			 }
			 cartItemRepository.save(cartItems);
			 orderRepository.save(activeOrder);
			 return activeOrder.getOrderDto();
			 
		 }
		 return null;
	}
	

	public OrderDto placeOrder(PlaceOrderDto placeOrderDto)
	{
		 Order activeOrder = orderRepository.findByUserIdAndOrderStatus(placeOrderDto.getUserId(),OrderStatus.Pending);
		 
		 Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
		 
		 if(optionalUser.isPresent())
		 {
			 activeOrder.setState(placeOrderDto.getState());
			 activeOrder.setPincode(placeOrderDto.getPincode());
			 activeOrder.setAddress(placeOrderDto.getAddress());
			 activeOrder.setDate(new Date());
			 activeOrder.setOrderStatus(OrderStatus.Placed);
			 activeOrder.setTrackingId(UUID.randomUUID());
			 
			 orderRepository.save(activeOrder);
			 
			 
			// This is for create new cart 
			 Order order  = new Order();
			 order.setAmount(0L);
			 order.setTotalAmount(0L);
			 order.setDiscount(0L);
			 order.setUser(optionalUser.get());
			 order.setOrderStatus(OrderStatus.Pending);
			 orderRepository.save(order);
			 
			 return activeOrder.getOrderDto();
		 }
		 return null;
	}
	
	
	
	public List<OrderDto> getMyPlacedOrders(Long userId)
	{
		return orderRepository.findByUserIdAndOrderStatusIn(userId, List.of(OrderStatus.Placed,OrderStatus.Shipped,
				OrderStatus.Delivered)).stream().map(Order:: getOrderDto).collect(Collectors.toList());
		
	}
	
	public OrderDto searchOrderByTrackingId(UUID trackingId)
	{
		 Optional<Order> optionalOrder = orderRepository.findByTrackingId(trackingId);
		 
		 if(optionalOrder.isPresent())
		 {
			 return optionalOrder.get().getOrderDto();
		 }
		 return null;
	}

}
