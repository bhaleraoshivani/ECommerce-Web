package com.ecommerce.entitiy;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ecommerce.dto.OrderDto;
import com.ecommerce.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order 
{
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long amount;
	
	private String state;
	
	private String address;
	
	private Long pincode;
	
	private Date date;
	
	private OrderStatus orderStatus;
	
	private Long totalAmount;
	
	private Long discount;
	
	private UUID trackingId;
	
	
	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "coupon_id",referencedColumnName = "id")
	private Coupon coupon;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	private List<CartItems> cartItems;

	
	
	 public OrderDto getOrderDto() {
		   
		   OrderDto orderDto = new OrderDto();
		   
		   orderDto.setId(id);
		   orderDto.setState(state);
		   orderDto.setAddress(address);
		   orderDto.setPincode(pincode);
		   orderDto.setTrackingId(trackingId);
		   orderDto.setAmount(amount);
		   orderDto.setDate(date);
		   orderDto.setOrderStatus(orderStatus);
		   orderDto.setUserName(user.getName());
		   orderDto.setMobile(user.getMobile());
		   
		   if(coupon != null)
		   {
			   orderDto.setCouponName(coupon.getName());
		   }
		   return orderDto;
		   
	   }



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public Coupon getCoupon() {
		return coupon;
	}



	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}



	public List<CartItems> getCartItems() {
		return cartItems;
	}



	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}



	
	
}
