package com.capgemini.canteenmanagementapplication.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "coupons")
public class Coupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long couponId;
	
	@Column(name = "coupon_code")
	private String code;
	
	@Column(name = "generate_date")
	private Date generateDate;
	
	@Column(name = "expire_date")
	private Date expireDate;
	
	@Column(name = "coupon_value")
	private double value;
	
	@Column(name = "order_id")
	private String orderId;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "custId")
	private Customer customer;

	public Coupon() {
		super();
	}
	

	public Coupon(String code, Date generateDate, Date expireDate, double value, String orderId) {
		super();
		this.code = code;
		this.generateDate = generateDate;
		this.expireDate = expireDate;
		this.value = value;
		this.orderId = orderId;
	}

	
	
	public Long getCouponId() {
		return couponId;
	}


	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getGenerateDate() {
		return generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return "Coupon [couponId=" + couponId + ", code=" + code + ", generateDate=" + generateDate + ", expireDate="
				+ expireDate + ", value=" + value + ", orderId=" + orderId + ", customer=" + customer + "]";
	}


	
	
	
	

}
