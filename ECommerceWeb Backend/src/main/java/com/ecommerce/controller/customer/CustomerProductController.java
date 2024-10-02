package com.ecommerce.controller.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.ProductDetailDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.entitiy.Category;
import com.ecommerce.services.customer.CustomerProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController 
{
	
	@Autowired
	private CustomerProductService customerProductService;
	
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> productDto = customerProductService.getAllProducts();
	    return ResponseEntity.ok(productDto);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsByTitle(@PathVariable String name)
	{
		List<ProductDto> productDto = customerProductService.getAllProductsByTitle(name);
	    return ResponseEntity.ok(productDto);
	}
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDetailDto> getProductDetailById(@PathVariable Long productId)
	{
		ProductDetailDto productDetailDto = customerProductService.getProductDetailById(productId);
		
		if(productDetailDto == null) 
		{
			 return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(productDetailDto);
	}
	
	
	
    @GetMapping("/categories")
    public List<Category> getAllCategories() 
    {
        return customerProductService.getAllCategories();
    }

 
    @GetMapping("/category/{categoryId}")
    public List<ProductDto> getProductsByCategory(@PathVariable Long categoryId)
    {
        return customerProductService.getProductsByCategory(categoryId);
    }

	

}
