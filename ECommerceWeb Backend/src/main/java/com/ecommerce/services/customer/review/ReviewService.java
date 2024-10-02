package com.ecommerce.services.customer.review;

import java.io.IOException;

import com.ecommerce.dto.OrderedProductResponseDto;
import com.ecommerce.dto.ReviewDto;

public interface ReviewService {
	
	OrderedProductResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
	
	 ReviewDto giveReview(ReviewDto reviewDto) throws IOException;

}
