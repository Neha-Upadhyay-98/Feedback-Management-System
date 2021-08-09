package com.capgemini.canteenmanagementapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.canteenmanagementapplication.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Long>{
	
	List<Coupon> findByCustomer_CustomerId(Long custId);

}
