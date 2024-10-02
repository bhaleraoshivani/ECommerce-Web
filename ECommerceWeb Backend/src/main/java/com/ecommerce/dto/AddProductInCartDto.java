package com.ecommerce.dto;

import lombok.Data;

@Data
public class AddProductInCartDto {
	
	private Long userId;
	private Long productId;
	
	
	public AddProductInCartDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public Long getProductId() {
		return productId;
	}


	public void setProductId(Long productId) {
		this.productId = productId;
	}


	
	
}
