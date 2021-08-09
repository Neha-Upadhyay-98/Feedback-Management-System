package com.capgemini.canteenmanagementapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.canteenmanagementapplication.exception.ResourceNotFoundException;
import com.capgemini.canteenmanagementapplication.model.Coupon;
import com.capgemini.canteenmanagementapplication.repository.CouponRepository;
import com.capgemini.canteenmanagementapplication.repository.CustomerRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class CouponController {
	
	@Autowired
	CouponRepository couponRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	//save coupon for specific customer
	@PostMapping("customers/{custId}/coupons")
	public Coupon saveCoupon(@PathVariable(value = "custId") Long custId, 
			@Valid @RequestBody Coupon coupon) throws ResourceNotFoundException
	{
		return customerRepository.findById(custId).map(customer -> {
			coupon.setCustomer(customer);
			return couponRepository.save(coupon);
		}).orElseThrow(()-> new ResourceNotFoundException("Customer not found"));
	}
	
	//get coupon by custId
	@GetMapping("customers/{custId}/coupons")
	public List<Coupon> getAllCouponsByCustId(@PathVariable(value = "custId") Long custId)
	{
		return couponRepository.findByCustomer_CustomerId(custId);
	}
	
	
	//update coupon
	@PutMapping("customers/{custId}/coupons/{couponId}")
	public Coupon updateCoupon(@PathVariable(value = "custId") Long custId,
			@PathVariable(value = "couponId") Long couponId, @Valid @RequestBody Coupon couponDetails) throws ResourceNotFoundException
	{
		return couponRepository.findById(couponId).map(coupon ->{
			coupon.setCode(couponDetails.getCode());
			coupon.setExpireDate(couponDetails.getExpireDate());
			coupon.setGenerateDate(coupon.getGenerateDate());
			coupon.setValue(coupon.getValue());
			coupon.setOrderId(couponDetails.getOrderId());
			
			return couponRepository.save(coupon);
		}).orElseThrow(()->new ResourceNotFoundException("coupon not found"));
	}
	
}
