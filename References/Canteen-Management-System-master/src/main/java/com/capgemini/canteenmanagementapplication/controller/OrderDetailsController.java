package com.capgemini.canteenmanagementapplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.canteenmanagementapplication.exception.ResourceNotFoundException;
import com.capgemini.canteenmanagementapplication.model.OrderDetails;
import com.capgemini.canteenmanagementapplication.model.Vendor;
import com.capgemini.canteenmanagementapplication.repository.OrderDetailsRepository;
import com.capgemini.canteenmanagementapplication.repository.OrderRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class OrderDetailsController {
	
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//save order details with corresponded orderId
	@PostMapping("/orders/{orderId}/orderdetails")
	public OrderDetails orderDetails(@PathVariable(value="orderId") String orderId,
			@Valid @RequestBody OrderDetails orderDetails) throws ResourceNotFoundException
	{
		return orderRepository.findById(orderId).map(order -> {
			orderDetails.setOrder(order);
			return orderDetailsRepository.save(orderDetails);
		}).orElseThrow(() -> new ResourceNotFoundException("order not found"));
	}
	
	//get all the order details by corresponding orderId
	@GetMapping("/orders/{orderId}/orderdetails")
	public List<OrderDetails> getAllOrderDetailsByOrderId(@PathVariable(value="orderId") String orderId)
	{
		return orderDetailsRepository.findByOrder_OrderId(orderId);
	}
	
	//delete order details by corresponding orderId
	@DeleteMapping("/orders/{orderId}/orderdetails")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "orderId") String orderId)
	   throws ResourceNotFoundException {
		
		List<OrderDetails> orderDetails = orderDetailsRepository.findByOrder_OrderId(orderId);
	    
		for (OrderDetails oD : orderDetails)
		{
			orderDetailsRepository.delete(oD);
		}
		
		return ResponseEntity.ok().build();
	}
	
	//get orderDetails by vendorId
	@GetMapping("/orders/vendorid={vendorId}/orderdetails")
	public List<OrderDetails> getOrderDetailsByVendorId(@PathVariable(value = "vendorId") Long vendorId)
	{
		return orderDetailsRepository.findByVendorId(vendorId);
	}
	
	//update orderDetails by orderId and orderDetailsId
	@PutMapping("/orders/orderdetails/{orderDetailsId}")
    public ResponseEntity<OrderDetails> updateVendor(@PathVariable(value = "orderDetailsId") Long orderDetailsId,
         @Valid @RequestBody OrderDetails orderDetails) throws ResourceNotFoundException {
        OrderDetails newOrderDetails = orderDetailsRepository.findById(orderDetailsId)
        .orElseThrow(() -> new ResourceNotFoundException("Vendor not found for this id :: " + orderDetailsId));

        newOrderDetails.setStatus(orderDetails.getStatus());
        newOrderDetails.setComments(orderDetails.getComments());
        
        final OrderDetails updatedOrerDetails = orderDetailsRepository.save(newOrderDetails);
        return ResponseEntity.ok(updatedOrerDetails);
    }

}
