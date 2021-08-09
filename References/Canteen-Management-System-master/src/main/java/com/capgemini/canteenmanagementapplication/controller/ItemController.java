package com.capgemini.canteenmanagementapplication.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.canteenmanagementapplication.exception.ResourceNotFoundException;
import com.capgemini.canteenmanagementapplication.model.Item;
import com.capgemini.canteenmanagementapplication.repository.ItemRepository;
import com.capgemini.canteenmanagementapplication.repository.VendorRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController 
@RequestMapping("/api/v1")
public class ItemController {
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	//get menu for corresponding vendor
	@GetMapping("/vendors/{vendorId}/menu")
	public List<Item> getMenuByVendorId(@PathVariable(value = "vendorId") Long vendorId)
	{
		return itemRepository.findByVendor_VendorId(vendorId);
	}
	
	//Get all items to create a menu
	@GetMapping("/menu")
	public List<Item> getMenu()
	{
		return itemRepository.findAll();
	}
	
	//Add a new item for corresponding vendor
	@PostMapping("/vendors/{vendorId}/menu")
	public Item saveItemByVendorId(@PathVariable(value = "vendorId") Long vendorId,
			@Valid @RequestBody Item item) throws ResourceNotFoundException
	{
		return vendorRepository.findById(vendorId).map(vendor ->{
			item.setVendor(vendor);
			return itemRepository.save(item);
		}).orElseThrow(() -> new ResourceNotFoundException("vendor not found"));
	}
	
	//Add a new item in menu
	@PostMapping("/menu")
	public Item createItem(@Valid @RequestBody Item item)
	{
		
		return itemRepository.save(item);
	}
	
	
	@GetMapping("/menu/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable(value = "itemId") Long itemId)
        throws ResourceNotFoundException {
        Item item = itemRepository.findById(itemId)
          .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));
        return ResponseEntity.ok().body(item);
    }
	
	
	@PutMapping("/menu/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable(value = "itemId") Long itemId,
         @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException
	{
        Item item = itemRepository.findById(itemId)
        .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));

        item.setItemName(itemDetails.getItemName());
        item.setItemPrice(itemDetails.getItemPrice());
        item.setDescription(itemDetails.getDescription());  
        
        final Item updatedItem = itemRepository.save(item);
        return ResponseEntity.ok(updatedItem);
    }
	
	//Delete an item according to itemId and vendorId
	@DeleteMapping("/vendors/{vendorId}/menu/{itemId}")
	public ResponseEntity<?> deleteItem(@PathVariable(value = "vendorId") Long vendorId, 
			@PathVariable(value = "itemId") Long itemId)
	throws ResourceNotFoundException
	{
		return itemRepository.findByItemIdAndVendor_VendorId(itemId, vendorId).map(item -> {
			itemRepository.delete(item);
		return ResponseEntity.ok().build();
		}).orElseThrow(()->new ResourceNotFoundException("item not found"));
	}
	
	
}
