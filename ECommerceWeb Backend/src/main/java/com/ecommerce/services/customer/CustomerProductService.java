package com.ecommerce.services.customer;

import java.util.List;
import com.ecommerce.dto.ProductDetailDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.entitiy.Category;


public interface CustomerProductService {
	
	List<ProductDto> getAllProducts();
	
	List<ProductDto> getAllProductsByTitle(String name);

	
	ProductDetailDto getProductDetailById(Long productId);
	
	

	List<Category> getAllCategories();

	List<ProductDto> getProductsByCategory(Long categoryId);
	
        

}
