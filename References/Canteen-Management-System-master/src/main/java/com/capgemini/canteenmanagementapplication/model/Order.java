package com.capgemini.canteenmanagementapplication.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order{
	
	@Id
	@Column(name="order_id")
	private String orderId;
	
	@Column(name = "custId")
	private Long customerId;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "order_date")
	private Date orderDate;
	
	@Column(name = "prepare_time")
	private Long prepTime;

	public Order() {
		super();
	}
	
	

	public Order(String orderId, Long customerId, double amount, String status, Date orderDate, Long prepTime) {
		super();
		this.orderId = orderId;
		this.customerId = customerId;
		this.amount = amount;
		this.status = status;
		this.orderDate = orderDate;
		this.prepTime = prepTime;
	}



	public Long getPrepTime() {
		return prepTime;
	}


	public void setPrepTime(Long prepTime) {
		this.prepTime = prepTime;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Date getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", customerId=" + customerId + ", amount=" + amount + ", status=" + status
				+ ", orderDate=" + orderDate + ", prepTime=" + prepTime + "]";
	}

	


	


	
	
	
	
}
