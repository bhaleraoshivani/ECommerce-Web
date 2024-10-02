package com.ecommerce.services.admin.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.ecommerce.entitiy.Coupon;
import com.ecommerce.exceptions.ValidationException;
import com.ecommerce.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService{
	
	@Autowired
	private CouponRepository couponRepository;
	
	
	public Coupon createCoupon(Coupon coupon)
	{
		if(couponRepository.existsByCode(coupon.getCode()))
		{
			throw new ValidationException("Coupon code already exists");
		}
		return couponRepository.save(coupon);
	}
	
	
	public List<Coupon> getAllCoupons()
	{
		return couponRepository.findAll();
				
	}
	

}
