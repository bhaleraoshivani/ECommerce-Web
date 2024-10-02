package com.ecommerce.services.admin.adminProduct;

import java.io.IOException;
import java.util.List;

import com.ecommerce.dto.ProductDto;

public interface AdminProductService {
	
	ProductDto addProduct(ProductDto productDto)throws IOException;
	
	public List<ProductDto> getAllProducts();
	
	List<ProductDto> getAllProductsByName(String name);
	
	boolean deleteProduct(Long id);
	
	ProductDto getProductById(Long productId);
	
	ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException;

}
