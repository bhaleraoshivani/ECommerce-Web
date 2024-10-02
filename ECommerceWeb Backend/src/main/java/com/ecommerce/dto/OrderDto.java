package com.ecommerce.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecommerce.enums.OrderStatus;

import lombok.Data;

@Data
public class OrderDto 
{
	
    private Long id;
	
	private Date date;
	
	private Long amount;
	
    private String state;
	
	private String address;
	
	private Long pincode;
	
	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	
	private UUID trackingId;
	
	private String userName;
	
	private String mobile;
	
	
	private List<CartItemsDto> cartItems;
	
	private String couponName;
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
	}

	public UUID getTrackingId() {
		return trackingId;
	}

	public void setTrackingId(UUID trackingId) {
		this.trackingId = trackingId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<CartItemsDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemsDto> cartItems) {
		this.cartItems = cartItems;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	

	
	
	


}
