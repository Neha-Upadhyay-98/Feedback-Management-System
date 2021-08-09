package com.capgemini.canteenmanagementapplication.model;

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
@Table(name="order_details")
public class OrderDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderDetailsId;
	
	@Column(name = "item_id")
	private Long itemId;
	
	@Column(name = "item_name", nullable = false)
	private String itemName;
	
	@Column(name = "unit_price", nullable = false)
	private double itemPrice;
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "total_price")
	private double totalPrice;
	
	@Column(name = "vendor_id")
	private Long vendorId;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "comments")
	private String comments;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "order_id")
	private Order order;

	public OrderDetails() {
		super();
	}

	public OrderDetails(Long itemId, String itemName, double itemPrice, long quantity, double totalPrice, Long vendorId,
			String status, String comments) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.vendorId = vendorId;
		this.status = status;
		this.comments = comments;
	}

	public Long getVendorId() {
		return vendorId;
	}


	public void setVendorId(Long vendorId) {
		this.vendorId = vendorId;
	}


	public Long getOrderDetailsId() {
		return orderDetailsId;
	}


	public void setOrderDetailsId(Long orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "OrderDetails [orderDetailsId=" + orderDetailsId + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", itemPrice=" + itemPrice + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", vendorId="
				+ vendorId + ", status=" + status + ", comments=" + comments + ", order=" + order + "]";
	}

	
	
	
	
	

}
