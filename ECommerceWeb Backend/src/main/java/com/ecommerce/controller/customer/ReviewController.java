package com.ecommerce.controller.customer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecommerce.dto.OrderedProductResponseDto;
import com.ecommerce.dto.ReviewDto;
import com.ecommerce.services.customer.review.ReviewService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class ReviewController 
{
	
	@Autowired
	private ReviewService  reviewService;
	
	
	@GetMapping("/ordered-products/{orderId}")
	public ResponseEntity<OrderedProductResponseDto> getOrderedProductsDetailsByOrderId(@PathVariable Long orderId)
	{
		return ResponseEntity.ok(reviewService.getOrderedProductsDetailsByOrderId(orderId));
	}
	
	
	@PostMapping("/review")
	public ResponseEntity<?> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException
	{
		ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
		
		if(reviewDto1 == null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
	}

}
