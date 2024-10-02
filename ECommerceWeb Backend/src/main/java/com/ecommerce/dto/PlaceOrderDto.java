package com.ecommerce.dto;

import lombok.Data;

@Data
public class PlaceOrderDto {
	
	private Long userId;
	
	private String state;
	
	private String address;
	
	private Long pincode;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	
	
	
	

}
