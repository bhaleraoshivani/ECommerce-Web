package com.ecommerce.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.FAQDto;
import com.ecommerce.dto.ProductDto;
import com.ecommerce.services.admin.adminProduct.AdminProductService;
import com.ecommerce.services.admin.faq.FAQService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminProductController
{
	
	@Autowired
	private AdminProductService adminProductService;
	
	@Autowired
	private FAQService faqService;
	
	
	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto)throws IOException
	{
		ProductDto productDto1 = adminProductService.addProduct(productDto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		List<ProductDto> productDto = adminProductService.getAllProducts();
	    return ResponseEntity.ok(productDto);
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name)
	{
		List<ProductDto> productDto = adminProductService.getAllProductsByName(name);
	    return ResponseEntity.ok(productDto);
	}
	
	
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long productId)
	{
		boolean deleted = adminProductService.deleteProduct(productId);
		
		if(deleted)
		{
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();	
	}
	
	
	@PostMapping("/faq/{productId}")
	public ResponseEntity<FAQDto> postFAQ(@PathVariable Long productId,@RequestBody FAQDto faqDto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(faqService.postFAQ(productId, faqDto));
	}
	
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId)
	{
		ProductDto productDto = adminProductService.getProductById(productId);
		
		if(productDto != null) {
			return ResponseEntity.ok(productDto);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	    
	}
	
	@PutMapping("/product/{productId}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,@ModelAttribute ProductDto productDto) throws IOException
	{
		ProductDto updatedProduct = adminProductService.updateProduct(productId, productDto);
		
		if(updatedProduct != null) {
			return ResponseEntity.ok(updatedProduct);
		}
		else {
			return ResponseEntity.noContent().build();
		}
	}
	
	
	
	
	

}
