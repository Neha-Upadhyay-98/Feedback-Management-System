package com.capgemini.canteenmanagementapplication.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.canteenmanagementapplication.exception.ResourceNotFoundException;
import com.capgemini.canteenmanagementapplication.model.Image;
import com.capgemini.canteenmanagementapplication.model.Item;
import com.capgemini.canteenmanagementapplication.repository.ImageRepository;
import com.capgemini.canteenmanagementapplication.repository.ItemRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class ImageController {
	
	@Autowired
	ImageRepository imageRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	
	//add image for corresponding item
	@PostMapping("/menu/{itemId}/itemImages")
	public Optional<Object> addImage(@PathVariable(value = "itemId") Long itemId,
			@RequestParam("itemImage") MultipartFile file) throws IOException
	{
		Image img = new Image(file.getOriginalFilename(), file.getContentType(), compressBytes(file.getBytes()));
		return itemRepository.findById(itemId).map(item ->{
			img.setItem(item);
			return imageRepository.save(img);
		});
	}
	
	//get corresponding image for item id
	@GetMapping("/menu/{itemId}/itemImages")
	public Image getImageByItemId(@PathVariable(value = "itemId") Long itemId)
	{
		final Optional<Image> retrievedImage = imageRepository.findByItem_ItemId(itemId);
		Image img = new Image(retrievedImage.get().getImageName(), 
				retrievedImage.get().getImageType(),
				decompressByte(retrievedImage.get().getPicByte()));
		img.setImageId(retrievedImage.get().getImageId());
		return img;
	}

	//get all images
	@GetMapping("/menu/images")
	public List<Image> getAllImages()
	{
		List<Image> images = imageRepository.findAll();
		
		for (Image image: images)
		{
			Image newImage = image;
			image.setPicByte(decompressByte(newImage.getPicByte()));
		}
		
		return images;
	}
	
	//update responding image by itemId
	@PutMapping("/menu/{itemId}/itemImages")
	public Optional<Object> updateImage(@PathVariable(value = "itemId") Long itemId,
			@RequestParam("itemImage") MultipartFile file) throws IOException
	{
		Image img = imageRepository.findByItem_ItemId(itemId).get();
		img.setImageName(file.getOriginalFilename());
		img.setImageType(file.getContentType());
		img.setPicByte(compressBytes(file.getBytes()));

		return itemRepository.findById(itemId).map(item ->{
			img.setItem(item);
			return imageRepository.save(img);
		});
		
		
	}
	
	//delete image for corresponding itemId
	@DeleteMapping("/menu/{itemId}/itemImages/{imageId}")
    public ResponseEntity<?> deleteImage(@PathVariable(value = "itemId") Long itemId, @PathVariable(value = "imageId") Long imageId)
         throws ResourceNotFoundException {
	
       return imageRepository.findByImageIdAndItem_ItemId(imageId, itemId).map(image -> {
    	 imageRepository.delete(image);
    	 return ResponseEntity.ok().build();
       })
       .orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + itemId));
       
	}
	
	
	private byte[] decompressByte(byte[] data) {
		// TODO Auto-generated method stub
		
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
		
	}

	private byte[] compressBytes(byte[] data) {
		// TODO Auto-generated method stub
		
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

}
