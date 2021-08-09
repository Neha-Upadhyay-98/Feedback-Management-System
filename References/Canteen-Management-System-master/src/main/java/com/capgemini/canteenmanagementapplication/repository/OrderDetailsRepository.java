package com.capgemini.canteenmanagementapplication.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.canteenmanagementapplication.model.OrderDetails;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long>{
	
	List<OrderDetails> findByOrder_OrderId(String orderId);
	List<OrderDetails> findByVendorId(Long vendorId);
	Optional<OrderDetails> findByOrderDetailsIdAndOrder_OrderId(Long orderDetailsId, String orderId);

}
