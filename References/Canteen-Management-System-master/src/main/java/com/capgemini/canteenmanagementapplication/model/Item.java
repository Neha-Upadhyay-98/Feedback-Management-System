package com.capgemini.canteenmanagementapplication.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "item_id")
	private long itemId;
	
	@Column(name = "item_name", nullable = false)
	private String itemName;
	
	@Column(name = "item_price", nullable = false)
	private double itemPrice;
	
	@Column(name = "item_description")
	private String description;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	public Item() {
		super();
	}


	public Item(String itemName, double itemPrice, String description) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.description = description;
	}


	public String getDescription() {
		return description;
	}


	
	public Vendor getVendor() {
		return vendor;
	}


	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}


	public void setDescription(String description) {
		this.description = description;
	}



	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
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


	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", description="
				+ description + ", vendor=" + vendor + "]";
	}




	

	



	
	
	
	
}
