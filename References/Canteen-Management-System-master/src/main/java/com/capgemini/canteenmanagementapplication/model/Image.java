package com.capgemini.canteenmanagementapplication.model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="item_image")
public class Image {
	
	@Id
	@Column(name="image_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long imageId;

	@Column(name="image_name")
	private String imageName;
	
	@Column(name = "image_type")
	private String imageType;
	
	@Column(name = "picByte", length = Integer.MAX_VALUE)
	private byte[] picByte;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "itemId")
	//@OnDelete(action = OnDeleteAction.CASCADE)
	private Item item;

	public Image() {
		super();
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Image(String imageName, String imageType, byte[] picByte) {
		super();
		this.imageName = imageName;
		this.imageType = imageType;
		this.picByte = picByte;
	}

	@Override
	public String toString() {
		return "Image [imageId=" + imageId + ", imageName=" + imageName + ", imageType=" + imageType + ", picByte="
				+ Arrays.toString(picByte) + ", item=" + item + "]";
	}

	
	
}