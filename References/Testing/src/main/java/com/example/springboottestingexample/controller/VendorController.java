package com.example.springboottestingexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboottestingexample.entity.Customer;
import com.example.springboottestingexample.entity.Vendor;
import com.example.springboottestingexample.exception.CustomerRegistrationException;
import com.example.springboottestingexample.exception.VendorRegistrationException;
import com.example.springboottestingexample.service.VendorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class VendorController {
	
	@Autowired
	private final VendorService vendorService;

	public VendorController(VendorService vendorService) {
		super();
		this.vendorService = vendorService;
	}
	
	@GetMapping
	public List<Vendor> getAllVendors(){
		return vendorService.findAllVendors();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendor> getVendorById(@PathVariable("id")Long id) {
		return vendorService.findVendorById(id)
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Vendor createVendor(@RequestBody @Validated Vendor vendor) throws VendorRegistrationException {
		return vendorService.createVendor(vendor);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Vendor> updateVendor(@PathVariable Long id, 
			@RequestBody Vendor vendor) {
		return vendorService.findVendorById(id)
				.map(VendorObj -> {
					vendor.setId(id);
					return ResponseEntity.ok(vendorService.updateVendor(vendor));
				})
				.orElseGet(() -> ResponseEntity.notFound().build());

	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Vendor> deleteCustomer(@PathVariable Long id) {
		return vendorService.findVendorById(id)
				.map(Vendor -> {
					vendorService.deleteVendorById(id);
					return ResponseEntity.ok(Vendor);
				})
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

}
