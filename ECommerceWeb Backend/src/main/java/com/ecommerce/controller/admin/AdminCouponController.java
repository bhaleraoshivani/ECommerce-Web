package com.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.entitiy.Coupon;
import com.ecommerce.exceptions.ValidationException;
import com.ecommerce.services.admin.coupon.AdminCouponService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin/coupons")
@RequiredArgsConstructor
public class AdminCouponController 
{
	@Autowired
	private AdminCouponService adminCouponService;
	
	
	@PostMapping
	public ResponseEntity<?> createCoupon(@RequestBody Coupon coupon)
	{
		try
		{
			Coupon createdCoupon = adminCouponService.createCoupon(coupon);
			return ResponseEntity.ok(createdCoupon);
		}
		catch(ValidationException ex)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	
	@GetMapping
	public  ResponseEntity<List<Coupon>> getAllCoupons()
	{
		return ResponseEntity.ok(adminCouponService.getAllCoupons());
	}

}
