package com.ecommerce.services.admin.coupon;

import java.util.List;

import com.ecommerce.entitiy.Coupon;

public interface AdminCouponService {
	
	Coupon createCoupon(Coupon coupon);
	
	List<Coupon> getAllCoupons();

}
