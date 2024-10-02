package com.ecommerce.services.customer.review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.OrderedProductResponseDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.dto.ReviewDto;
import com.ecommerce.entitiy.CartItems;
import com.ecommerce.entitiy.Order;
import com.ecommerce.entitiy.Product;
import com.ecommerce.entitiy.Review;
import com.ecommerce.entitiy.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ReviewRepository;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	
	
	
	public OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId)
	{
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		
		OrderedProductResponseDto  orderedProductResponseDto = new OrderedProductResponseDto();
		
		if(optionalOrder.isPresent())
		{
			orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
			
			List<ProductDto> productDtoList = new ArrayList<>();
			for(CartItems cartItems: optionalOrder.get().getCartItems()) 
			{
				ProductDto productDto = new ProductDto();
				
				productDto.setId(cartItems.getProduct().getId());
				productDto.setName(cartItems.getProduct().getName());
				productDto.setPrice(cartItems.getProduct().getPrice());
				productDto.setQuantity(cartItems.getQuantity());
				
				productDto.setByteImg(cartItems.getProduct().getImg());
				
				productDtoList.add(productDto);				
			}
			orderedProductResponseDto.setProductDtoList(productDtoList);
		}
		return orderedProductResponseDto;
	}
	
	
   public ReviewDto giveReview(ReviewDto reviewDto) throws IOException
   {
	   Optional<Product> optionalProduct = productRepository.findById(reviewDto.getProductId());
	   
	   Optional<User> optionalUser = userRepository.findById(reviewDto.getUserId());
	   
	   if(optionalProduct.isPresent() && optionalUser.isPresent())
	   {
		   Review review = new Review();
		   
		   review.setRating(reviewDto.getRating());
		   review.setDescription(reviewDto.getDescription());
		   review.setUser(optionalUser.get());
		   review.setProduct(optionalProduct.get());
		   review.setImg(reviewDto.getImg().getBytes());
		   
		   return reviewRepository.save(review).getDto();
		   
	    }
	   return null;
	   
   }
	
	

}
