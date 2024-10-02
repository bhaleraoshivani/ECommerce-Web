package com.ecommerce.services.customer;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDetailDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.entitiy.Category;
import com.ecommerce.entitiy.FAQ;
import com.ecommerce.entitiy.Product;
import com.ecommerce.entitiy.Review;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.FAQRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {
	
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private FAQRepository faqRepository;
	
	@Autowired
	private ReviewRepository  reviewRepository;
	
	
	
	public List<ProductDto> getAllProducts()
	{
		List<Product> products = productRepository.findAll();
		
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	public List<ProductDto> getAllProductsByTitle(String name)
	{
		List<Product> products = productRepository.findAllByNameContaining(name);
		
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	

	
	
	public ProductDetailDto getProductDetailById(Long productId)
	{
		Optional<Product> optionalProduct = productRepository.findById(productId);
		
		if(optionalProduct.isPresent())
		{
			List<FAQ> faqList = faqRepository.findAllByProductId(productId);
			
			List<Review> reviewList = reviewRepository.findAllByProductId(productId);
			
			ProductDetailDto productDetailDto = new ProductDetailDto();
			
			productDetailDto.setProductDto(optionalProduct.get().getDto());
			productDetailDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
			productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
			
			return productDetailDto;
			
		}
		return null;
		
	}
	
	
	
	
	// Fetch all categories
    public List<Category> getAllCategories() 
    {
        return categoryRepository.findAll();
    }

    
   
    // Fetch products by category
	public List<ProductDto> getProductsByCategory(Long categoryId)
	{
		List<Product> products = productRepository.findByCategoryId(categoryId);
		
		return products.stream().map(Product::getDto).collect(Collectors.toList());
	}
	
	

	

	

}
